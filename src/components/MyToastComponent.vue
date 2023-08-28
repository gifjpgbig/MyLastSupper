<template>
  <div class="t-container">
    <span>Toasts {{ counter }} from toasts!</span>
    <button class="action" @click.stop="clicked">Open!</button>
  </div>
</template>
<script>
export default {
  props: ["counter"],
  methods: {
    clicked() {
      // Emit a "click" event when clicked.
      // Can be any event though and even pass parameters back
      this.$emit("click");
    }
  }
};
</script>
<style>
.t-container {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.action {
  height: 40px;
  padding: 5px 10px;
  border-radius: 5px;
  color: white;
  cursor: pointer;
  border: thin solid currentColor;
}
</style>
