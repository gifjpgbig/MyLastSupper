<template>
        <h2>Contact Page</h2>
        <button
    type="button"
    class="btn btn-primary"
    data-bs-toggle="modal"
    data-bs-target="#exampleModal"
  >
    按下去顯示Modal
  </button>

  <!-- Modal -->
  <div
    class="modal fade"
    id="exampleModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Modal 標題文字</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">...</div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            關閉
          </button>
          <button type="button" class="btn btn-primary">確定</button>
        </div>
      </div>
    </div>
  </div>
</template>
    
<script setup>
// import 'bootstrap/dist/css/bootstrap.min.css'
// import 'bootstrap/dist/js/bootstrap.js'
// import 'bootstrap-icons/font/bootstrap-icons.css'
</script>
    
<style>
    
</style>