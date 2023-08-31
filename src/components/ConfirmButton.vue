<template>

    <button :class="buttonClass" type="button">

    <slot></slot>
    </button>

</template>
    
<script setup>
    import { defineProps, computed, ref } from 'vue';

    const props = defineProps({
        primary: Boolean,
        secondary: Boolean
    });

    const buttonClass = computed(()=>{

        if(props.primary){
            return 'btn btn-primary';
        }else{
            return 'btn btn-secondary';
        }
    });

</script>
    
<style>
    
</style>