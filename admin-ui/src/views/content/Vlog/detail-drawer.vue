<template>
  <BasicDrawer v-bind="$attrs" @register="registerDrawer" title="查看详情" width="40%">
    <Description
      size="large"
      title=""
      :bordered="true"
      :column="1"
      :data="record"
      :schema="retrieveDetailFormSchema"
    />
  </BasicDrawer>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import { Description } from '/@/components/Description';
  import { retrieveDetailFormSchema } from './data';
  import { retrieveVlogApi } from '/@/api/content/VlogApi';

  const record = ref({});

  export default defineComponent({
    name: 'VlogDetailDrawer',
    components: { BasicDrawer, Description },
    emits: ['success', 'register'],
    setup(_) {
      const [registerDrawer] = useDrawerInner(async (data) => {
        record.value = await retrieveVlogApi(data.record.id);
      });

      return {
        registerDrawer,
        record,
        retrieveDetailFormSchema,
      };
    },
  });
</script>
