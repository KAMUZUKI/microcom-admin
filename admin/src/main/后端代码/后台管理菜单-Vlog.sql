-- 菜单
    INSERT INTO sys_menu (id, tenant_id, revision, del_flag, created_at, created_by, updated_at, updated_by,
                          title,
                          parent_id, type, permission, icon, sort, status, component)
    VALUES (20231129104749696, NULL, 1, 0, NOW(), 'helio-generator', NOW(), 'helio-generator', '管理', 0, 1,
                             'Vlog', 'ant-design:flag-outlined', 100, 1, '/content/Vlog/index');

-- 子按钮
    INSERT INTO sys_menu (id, tenant_id, revision, del_flag, created_at, created_by, updated_at, updated_by,
                          title, parent_id,
                          type, permission, icon, sort, status)
    VALUES (20231129104749697, NULL, 1, 0, NOW(), 'helio-generator', NOW(), 'helio-generator', '查询', 20231129104749696,
                             2,
                             'Vlog:retrieve', NULL, 1, 1);

    INSERT INTO sys_menu (id, tenant_id, revision, del_flag, created_at, created_by, updated_at, updated_by,
                          title, parent_id,
                          type,
                          permission, icon,
                          sort, status)
    VALUES (20231129104749698, NULL, 1, 0, NOW(), 'helio-generator', NOW(), 'helio-generator', '新增', 20231129104749696,
                             2,
                             'Vlog:create', NULL, 2, 1);

    INSERT INTO sys_menu (id, tenant_id, revision, del_flag, created_at, created_by, updated_at, updated_by,
                          title, parent_id,
                          type,
                          permission, icon,
                          sort, status)
    VALUES (20231129104749699, NULL, 1, 0, NOW(), 'helio-generator', NOW(), 'helio-generator', '编辑', 20231129104749696,
                             2,
                             'Vlog:update', NULL, 4, 1);

    INSERT INTO sys_menu (id, tenant_id, revision, del_flag, created_at, created_by, updated_at, updated_by,
                          title, parent_id,
                          type,
                          permission, icon,
                          sort, status)
    VALUES (20231129104749700, NULL, 1, 0, NOW(), 'helio-generator', NOW(), 'helio-generator', '删除', 20231129104749696,
                             2,
                             'Vlog:delete', NULL, 3, 1);

