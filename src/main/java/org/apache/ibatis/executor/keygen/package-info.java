/**
 *    Copyright 2009-2015 the original author or authors.
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
/**
 * 插入操作返回自增主键
 * Contains the key generators
 */
package org.apache.ibatis.executor.keygen;
/*
demo-1

<insert id="insertAndgetkey" parameterType="com.soft.mybatis.model.User">
<!--selectKey  会将 SELECT LAST_INSERT_ID()的结果放入到传入的model的主键里面，
  keyProperty 对应的model中的主键的属性名，这里是 user 中的id，因为它跟数据库的主键对应
  order AFTER 表示 SELECT LAST_INSERT_ID() 在insert执行之后执行,多用与自增主键，
  BEFORE 表示 SELECT LAST_INSERT_ID() 在insert执行之前执行，这样的话就拿不到主键了，
  这种适合那种主键不是自增的类型
  resultType 主键类型 -->
<selectKey keyProperty="id" order="AFTER" resultType="java.lang.Integer">
  SELECT LAST_INSERT_ID()
</selectKey>
  insert into t_user (username,password,create_date) values(#{username},#{password},#{createDate})
</insert>

*/
