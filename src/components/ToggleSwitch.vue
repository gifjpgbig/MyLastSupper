<template>
  <div id="app">
    <div class="panel panel-default">
      <div class="panel-body">
        <!--Only code you need is this label-->
        <label class="switch">
          <input type="checkbox" @click="toggleCheckbox" v-model="checkbox1"/>
          <div class="slider round"></div>
        </label>
        <!-- <p>{{ checkbox }}</p> -->
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from 'vue';
const checkbox1 = ref("")
const emit = defineEmits(["toggle"])


const toggleCheckbox = () => {
  checkbox1.value = !checkbox1.value;
  // Emit the custom event
  emit('toggle', checkbox1.value);
};


</script>

<style scoped>
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input {
  display: none;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

input:checked + .slider {
  background-color: #101010;
}

input:focus + .slider {
  box-shadow: 0 0 1px #101010;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}

</style>
