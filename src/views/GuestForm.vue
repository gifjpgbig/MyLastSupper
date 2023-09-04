<!-- <template>
  <div
    class="modal fade"
    id="guestModal"
    tabindex="-1"
    aria-labelledby="guestLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5>初次登入</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <Vueform>
            <GroupElement name="container">
              <TextareaElement
                name="textarea"
                label="申請書"
                placeholder="please enter some text"
              />
               <FileElement
        name="file"
        label="File"
      />
      <MultifileElement
        name="multiImage"
        label="Multi-image"
        view="image"
        accept="image/*"
        :file="{
          rules: [
            'mimetypes:image/jpeg,image/png,image/gif,image/webp,image/svg+xml,image/tiff',
          ],
        }"
      /> 
            </GroupElement>
          </Vueform>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            關閉
          </button>
        </div>
      </div>
    </div>
  </div>
  <div>
    <button
      class="btn btn-secondary me-3"
      type="button"
      data-bs-toggle="modal"
      data-bs-target="#guestModal"
      @click="showModal = true"
    >
      <i class="bi bi-trash-fill"></i> 詳細
    </button>
  </div>
</template>

<script setup></script>

<style scoped></style> -->

<template>
  <!-- 注意：el-table 上加一个 ref="table" -->
  <p>
  <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Link with href
  </a>
  
  <div>
      <a href="#collapseExample" role="button" data-bs-toggle="collapse">
    Some placeholder content for the collapse component.
</a>
</div>

  <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    Button with data-bs-target
  </button>
</p>
<div class="collapse" id="collapseExample">
  <div class="card card-body">
    Some placeholder content for the collapse component. This panel is hidden by default but revealed when the user activates the relevant trigger.
  </div>
</div>
</template>

<script>

</script>

<style scoped>
/* 展开行样式 */
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 100%;
}
.el-form-item__content {
  width: 100%;
}
/* 隐藏上方表格多余部分 */
.undefined.el-table__expand-column {
  display: none;
}
/* 隐藏上方表格多余部分 */
.el-table_1_column_8 .el-table--border td,
.el-table--border th,
.el-table__body-wrapper .el-table--border.is-scrolling-left ~ .el-table__fixed {
  border-right: 0px solid #ebeef5;
}
</style>
