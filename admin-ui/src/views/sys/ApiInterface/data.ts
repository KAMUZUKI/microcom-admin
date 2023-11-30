import { BasicColumn, FormSchema } from '/@/components/Table';
import { DescItem } from '/@/components/Description';

/**
 * 表格列
 */
export const columns: BasicColumn[] = [
  {
    title: '接口名称或标识',
    dataIndex: 'name',
    width: 80,
    ellipsis: false,
  },
  {
    title: '接口路径',
    dataIndex: 'path',
    width: 80,
    ellipsis: false,
  },
  {
    title: '请求方法',
    dataIndex: 'method',
    width: 60,
  },
  {
    title: '请求参数',
    dataIndex: 'requestParameters',
    width: 80,
  },
  {
    title: '返回数据结构',
    dataIndex: 'responseData',
    width: 80,
  },
  {
    title: '接口示例',
    dataIndex: 'description',
    width: 80,
  },
  {
    title: '接口所需的权限或角色',
    dataIndex: 'permissions',
    width: 100,
  },
  {
    title: '接口版本号',
    dataIndex: 'version',
    width: 60,
  },
  {
    title: '创建时间',
    dataIndex: 'createdAt',
    width: 80,
    ellipsis: false,
  },
];

/**
 * 查询条件
 */
export const queryFormSchema: FormSchema[] = [
  {
    field: 'name',
    label: '接口名称或标识',
    component: 'Input',
    componentProps: {},
    colProps: { span: 8 },
  },
  {
    field: 'path',
    label: '接口路径',
    component: 'Input',
    componentProps: {},
    colProps: { span: 8 },
  },
  {
    field: '[beginAt, endAt]',
    label: '创建时间区间',
    component: 'RangePicker',
    componentProps: {
      valueFormat: 'YYYY-MM-DD',
      placeholder: ['开始时间', '结束时间'],
      // 不显示时分秒部分
      showTime: false,
    },
    colProps: { span: 8 },
  },
];

/**
 * 查看详情表单
 */
export const retrieveDetailFormSchema: DescItem[] = [
  {
    field: 'name',
    label: '接口名称或标识',
  },
  {
    field: 'path',
    label: '接口路径',
  },
  {
    field: 'method',
    label: '请求方法',
  },
  {
    field: 'requestParameters',
    label: '请求参数',
  },
  {
    field: 'responseData',
    label: '返回数据结构',
  },
  {
    field: 'description',
    label: '接口示例',
  },
  {
    field: 'permissions',
    label: '接口所需的权限或角色',
  },
  {
    field: 'version',
    label: '接口版本号',
  },
  {
    field: 'createdAt',
    label: '创建时间',
  },
  {
    field: 'updatedAt',
    label: '更新时间',
  },
];

/**
 * 新增/编辑表单
 */
export const insertOrUpdateFormSchema: FormSchema[] = [
  {
    field: 'id',
    // 只是为了把ID带过来
    label: '主键ID',
    component: 'Render',
    show: false,
  },
  {
    field: 'name',
    label: '接口名称或标识',
    required: true,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'path',
    label: '接口路径',
    required: true,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'method',
    label: '请求方法',
    required: true,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'requestParameters',
    label: '请求参数',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'responseData',
    label: '返回数据结构',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'description',
    label: '接口示例',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'permissions',
    label: '接口所需的权限或角色',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'version',
    label: '接口版本号',
    required: false,
    component: 'Input',
    componentProps: {},
  },
];
