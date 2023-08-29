<script setup>
import { useToast } from 'vue-toastification';
const toast = useToast();

const success = () => toast.success('You did it! 🎉');
</script>

<template>
  <div class="grid grid-cols-1">
    <div>
      <button
        class="bg-green-500 font-bold text-white py-2 px-4 my-2 rounded"
        @click="success"
      >
        Show success
      </button>
    </div>
  </div>
</template>

<style scoped>
#app {
  font-family: Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  align-items: center;
  display: flex;
  justify-content: center;
  height: 100vh;
}

button {
  padding: 0.75em 2em;
  border-radius: 2em;
  display: inline-block;
  justify-content: center;
  color: #fff;
  background-color: #4fc08d;
  box-sizing: border-box;
  border: 1px solid #fff;
  cursor: pointer;
  font-size: 1.05em;
  letter-spacing: 0.1em;
  text-transform: uppercase;
}
</style>
