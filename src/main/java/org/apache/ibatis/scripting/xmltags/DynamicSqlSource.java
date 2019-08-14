/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.scripting.xmltags;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

/**
 * 解析动态sql
 * @author Clinton Begin
 */
public class DynamicSqlSource implements SqlSource {

  private final Configuration configuration;
  private final SqlNode rootSqlNode;

  public DynamicSqlSource(Configuration configuration, SqlNode rootSqlNode) {
    this.configuration = configuration;
    this.rootSqlNode = rootSqlNode;
  }

  /**
   * 获取可执行的sql  BoundSql
   * (1)转换${} 是字符串替换-->有sql注入风险 比如 id=${id}-->id = 1
   * (2)转换#{} 是预编译处理-->会在值加上单引号 比如 id=#{id} -->id = '1'
   * (3)转换? 将问号预编译处理转换为绑定的数值
   * @param parameterObject
   * @return
   */
  @Override
  public BoundSql getBoundSql(Object parameterObject) {
    //封装动态SQL上下文
    DynamicContext context = new DynamicContext(configuration, parameterObject);
    //rootSqlNode将上下问封装到sql节点中 执行完 rootSqlNode.apply(context); DynamicContext.sqlBuilder就有一个完整的sql了
    //（1）将sql中的${}号转换为数值
    rootSqlNode.apply(context);

    //（2）将sql中的#{}号转换为?号
    SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
    Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
    SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
    BoundSql boundSql = sqlSource.getBoundSql(parameterObject);

    //（3）将sql中的?号转换为Bindings中的参数值
    context.getBindings().forEach(boundSql::setAdditionalParameter);
    return boundSql;
  }

}
