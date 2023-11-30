import { defHttp } from '/@/utils/http/axios';
import { VlogApiResult, VlogInsertOrUpdateForm } from './model/VlogModel';

enum Api {
  REST = '/api/v1/vlogs',
}

/**
 * -分页列表
 */
export const listVlogApi = (queryForm: any) => {
  // 选择的是日期，补全时分秒部分
  if (queryForm.beginAt) {
    queryForm.beginAt += ' 00:00:00';
  }

  if (queryForm.endAt) {
    queryForm.endAt += ' 23:59:59';
  }

  return defHttp.get<VlogApiResult[]>({
    url: Api.REST,
    params: queryForm,
  });
};

/**
 * -详情
 */
export const retrieveVlogApi = (id: string) => {
  return defHttp.get<VlogApiResult>({
    url: `${Api.REST}/${id}`,
  });
};

/**
 * -新增
 */
export const insertVlogApi = (insertForm: VlogInsertOrUpdateForm) => {
  return defHttp.post<void>({
    url: Api.REST,
    params: insertForm,
  });
};

/**
 * -编辑
 */
export const updateVlogApi = (id: string, updateForm: VlogInsertOrUpdateForm) => {
  return defHttp.put<void>({
    url: `${Api.REST}/${id}`,
    params: updateForm,
  });
};

/**
 * -删除
 */
export const deleteVlogApi = (ids: string[]) => {
  return defHttp.delete<void>({
    url: Api.REST,
    params: {
      ids: ids,
    },
  });
};
