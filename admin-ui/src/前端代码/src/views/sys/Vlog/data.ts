import { BasicColumn, FormSchema } from '/@/components/Table';
import { DescItem } from '/@/components/Description';

/**
 * 表格列
 */
export const columns: BasicColumn[] = [
  {
    title: '',
    dataIndex: 'createId',
    width: 80,
  },
  {
    title: '作者名',
    dataIndex: 'author',
    width: 80,
  },
  {
    title: '',
    dataIndex: 'title',
    width: 80,
  },
  {
    title: '',
    dataIndex: 'label',
    width: 80,
  },
  {
    title: '',
    dataIndex: 'text',
    width: 80,
  },
  {
    title: '',
    dataIndex: 'img',
    width: 80,
  },
  {
    title: '',
    dataIndex: 'time',
    width: 80,
  },
  {
    title: '',
    dataIndex: 'status',
    width: 80,
  },
  {
    title: '创建时间',
    dataIndex: 'createdAt',
    width: 80,
  },
];

/**
 * 查询条件
 */
export const queryFormSchema: FormSchema[] = [
  {
    field: 'createId',
    label: '',
    component: 'Input',
    componentProps: {},
    colProps: { span: 8 },
  },
  {
    field: 'author',
    label: '作者名',
    component: 'Input',
    componentProps: {},
    colProps: { span: 8 },
  },
  {
    field: 'title',
    label: '',
    component: 'Input',
    componentProps: {},
    colProps: { span: 8 },
  },
  {
    field: 'label',
    label: '',
    component: 'Input',
    componentProps: {},
    colProps: { span: 8 },
  },
  {
    field: 'text',
    label: '',
    component: 'Input',
    componentProps: {},
    colProps: { span: 8 },
  },
  {
    field: 'img',
    label: '',
    component: 'Input',
    componentProps: {},
    colProps: { span: 8 },
  },
  {
    field: 'time',
    label: '',
    component: 'Input',
    componentProps: {},
    colProps: { span: 8 },
  },
  {
    field: 'status',
    label: '',
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
    field: 'createId',
    label: '',
  },
  {
    field: 'author',
    label: '作者名',
  },
  {
    field: 'title',
    label: '',
  },
  {
    field: 'label',
    label: '',
  },
  {
    field: 'text',
    label: '',
  },
  {
    field: 'img',
    label: '',
  },
  {
    field: 'time',
    label: '',
  },
  {
    field: 'status',
    label: '',
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
    field: 'createId',
    label: '',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'author',
    label: '作者名',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'title',
    label: '',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'label',
    label: '',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'text',
    label: '',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'img',
    label: '',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'time',
    label: '',
    required: false,
    component: 'Input',
    componentProps: {},
  },
  {
    field: 'status',
    label: '',
    required: true,
    component: 'Input',
    componentProps: {},
  },
];
