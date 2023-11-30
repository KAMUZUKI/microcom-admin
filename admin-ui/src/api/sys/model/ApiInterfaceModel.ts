/**
 * @model 接口管理
 */

/*
 ---- REQUEST ----
 */

/**
 * -新增/编辑请求体
 */
export interface ApiInterfaceInsertOrUpdateForm {
  /**
   * 接口名称或标识
   */
  name: string;

  /**
   * 接口路径
   */
  path: string;

  /**
   * 请求方法
   */
  method: string;

  /**
   * 请求参数，包括参数名称、数据类型、是否必需、默认值等信息
   */
  requestParameters: string;

  /**
   * 返回数据结构，包括返回字段、数据类型、示例值等信息
   */
  responseData: string;

  /**
   * 接口示例
   */
  description: string;

  /**
   * 接口所需的权限或角色
   */
  permissions: string;

  /**
   * 接口版本号
   */
  version: string;
}

/*
 ---- RESPONSE ----
 */

/**
 * -通用响应体
 */
export type ApiInterfaceApiResult = ApiInterfaceInsertOrUpdateForm & {
  /**
   * 主键ID
   */
  id: string;

  /**
   * 创建时刻
   */
  createdAt: string;

  /**
   * 更新时刻
   */
  updatedAt: string;
};
