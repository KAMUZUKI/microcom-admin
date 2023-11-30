/**
 * @model
 */

/*
 ---- REQUEST ----
 */

/**
 * -新增/编辑请求体
 */
export interface VlogInsertOrUpdateForm {
  /**
   *
   */
  createId: string;

  /**
   * 作者名
   */
  author: string;

  /**
   *
   */
  title: string;

  /**
   *
   */
  label: string;

  /**
   *
   */
  text: string;

  /**
   *
   */
  img: string;

  /**
   *
   */
  time: string;

  /**
   *
   */
  status: string;
}

/*
 ---- RESPONSE ----
 */

/**
 * -通用响应体
 */
export type VlogApiResult = VlogInsertOrUpdateForm & {
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
