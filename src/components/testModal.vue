<template>

        <div class="modal fade" id="stepModal" tabindex="-1" role="dialog" aria-labelledby="stepModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="stepModalLabel">Step Form Modal</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <!-- 这里是表单内容 -->
        <div class="step-content">
          <!-- 第一页的内容 -->
          <div class="step step-1">
            <h3>Step 1</h3>
            <input type="text" placeholder="Field 1">
            <button class="next-step">Next</button>
          </div>
          <!-- 第二页的内容 -->
          <div class="step step-2">
            <h3>Step 2</h3>
            <input type="text" placeholder="Field 2">
            <button class="prev-step">Previous</button>
            <button class="next-step">Next</button>
          </div>
          <!-- 第三页的内容 -->
          <div class="step step-3">
            <h3>Step 3</h3>
            <input type="text" placeholder="Field 3">
            <button class="prev-step">Previous</button>
            <button class="next-step">Next</button>
          </div>
          <!-- ... 可以继续添加其他步骤 -->
        </div>
      </div>
    </div>
  </div>
</div>
</template>
    
<script setup>
    const $ = window.jQuery
    $(document).ready(function () {
  // 初始化 Modal
  $('#stepModal').modal({
    backdrop: 'static',  // 点击背景不关闭 Modal
    keyboard: false      // 按下 ESC 不关闭 Modal
  });

  // 初始步骤
  var currentStep = 1;

  // 下一步按钮点击事件
  $('.next-step').on('click', function () {
    if (currentStep < 3) {  // 假设有 3 个步骤
      $('.step-' + currentStep).hide();
      currentStep++;
      $('.step-' + currentStep).show();
    }
  });

  // 上一步按钮点击事件
  $('.prev-step').on('click', function () {
    if (currentStep > 1) {
      $('.step-' + currentStep).hide();
      currentStep--;
      $('.step-' + currentStep).show();
    }
  });

  // 在最后一页隐藏下一步按钮
  if (currentStep === 3) {
    $('.next-step').hide();
  }
});
</script>
    
<style>
    
</style>