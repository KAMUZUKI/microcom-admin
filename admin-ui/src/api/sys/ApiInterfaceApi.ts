import { defHttp } from '/@/utils/http/axios';
import { ApiInterfaceApiResult, ApiInterfaceInsertOrUpdateForm } from './model/ApiInterfaceModel';

enum Api {
  REST = '/api/v1/api-interfaces',
}

/**
 * -分页列表
 */
export const listApiInterfaceApi = (queryForm: any) => {
  // 选择的是日期，补全时分秒部分
  if (queryForm.beginAt) {
    queryForm.beginAt += ' 00:00:00';
  }

  if (queryForm.endAt) {
    queryForm.endAt += ' 23:59:59';
  }

  return defHttp.get<ApiInterfaceApiResult[]>({
    url: Api.REST,
    params: queryForm,
  });
};

/**
 * -详情
 */
export const retrieveApiInterfaceApi = (id: string) => {
  return defHttp.get<ApiInterfaceApiResult>({
    url: `${Api.REST}/${id}`,
  });
};

/**
 * -新增
 */
export const insertApiInterfaceApi = (insertForm: ApiInterfaceInsertOrUpdateForm) => {
  return defHttp.post<void>({
    url: Api.REST,
    params: insertForm,
  });
};

/**
 * -编辑
 */
export const updateApiInterfaceApi = (id: string, updateForm: ApiInterfaceInsertOrUpdateForm) => {
  return defHttp.put<void>({
    url: `${Api.REST}/${id}`,
    params: updateForm,
  });
};

/**
 * -删除
 */
export const deleteApiInterfaceApi = (ids: string[]) => {
  return defHttp.delete<void>({
    url: Api.REST,
    params: {
      ids: ids,
    },
  });
};
