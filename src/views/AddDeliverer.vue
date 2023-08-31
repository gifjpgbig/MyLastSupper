<template>

 <div class="row">
 <div class="col-3"></div>
 <div class="col-6">
    <h2>外送員 新增</h2>
   <div class="mb-3">
     <label for="name" class="form-label">姓名</label>
     <input type="text" class="form-control" id="name" v-model="deliverer.name" @input="checkName"/>
     <div v-if="nameError" class="text-danger">{{nameError}}</div>
   </div>

   <div class="mb-3">
     <label for="account" class="form-label">帳號</label>
     <input type="text" class="form-control" id="account" v-model="deliverer.account" @input="checkAccount"/>
     <div v-if="accountError" class="text-danger">{{accountError}}</div>
   </div>
   <div class="mb-3">
     <label for="password" class="form-label">密碼</label>
     <input type="text" class="form-control" id="password" v-model="deliverer.password" @input="checkPassword"/>
     <div v-if="passwordError" class="text-danger">{{passwordError}}</div>
   </div>
   <div class="mb-3">
     <label for="phone" class="form-label">手機號碼</label>
     <input type="text" class="form-control" id="phone" v-model="deliverer.phone" @input="checkPhone"/>
     <div v-if="phoneError" class="text-danger">{{phoneError}}</div>
   </div>
   <div class="mb-3">
     <label for="birthday" class="form-label">出生日期</label>
     <input type="date" class="form-control" id="birthday" v-model="deliverer.birthday" @input="checkBirthday"/>
     <div v-if="birthdayError" class="text-danger">{{birthdayError}}</div>
   </div>
   <div class="mb-3">
     <label for="email" class="form-label">Email</label>
     <input type="text" class="form-control" id="email" v-model="deliverer.email" @input="checkEmail"/>
     <div v-if="emailError" class="text-danger">{{emailError}}</div>
    </div>

        <!-- ~原版~
        <button :class="addButtonClass" type="button" @click="addHandler">新增</button> -->
        <ConfirmButton :primary="addButtonClass" @click="addHandler">新增</ConfirmButton>


      </div>
    </div>

  </template>
      
  <script setup>
  import { computed, ref } from 'vue';
  import axios from 'axios';
  import { useRouter } from 'vue-router';
  import Deliverer from '../models/Deliverer';
  import ConfirmButton from '../components/ConfirmButton.vue'

  const router = useRouter()
  const deliverer = ref(Deliverer)
  const nameError=ref('')
  const accountError=ref('')
  const passwordError=ref('')
  const phoneError=ref('')
  const birthdayError=ref('')
  const emailError=ref('')

  const addButtonClass = computed(()=>{
        if(
            (!deliverer.value.name || deliverer.value.name.trim()==='') ||
            (!deliverer.value.account || deliverer.value.account.trim()==='') ||
            (!deliverer.value.password || deliverer.value.password.trim()==='') ||
            (!deliverer.value.phone || deliverer.value.phone.trim()==='') ||
            (!deliverer.value.birthday || deliverer.value.birthday.trim()==='') ||
            (!deliverer.value.email || deliverer.value.email.trim()==='')
        )
        // {
        //     return 'btn btn-secondary'
        // }else{
        //     return 'btn btn-primary'
        // }
        {
            return false
        }else{
            return true
        }
    });

    const checkName=()=>{
        if(deliverer.value.name.length>12){
            nameError.value='姓名不能超過12個字';
        }else{
            nameError.value='';
        }
    };
    const checkAccount=()=>{
        if(deliverer.value.account.length>20){
            accountError.value='帳號不能超過20個字';
        }else{
            accountError.value='';
        }
    };
    const checkPassword=()=>{
        if(deliverer.value.password.length>20){
            passwordError.value='密碼不能超過20個字';
        }else{
            passwordError.value='';
        }
    };
    const checkPhone=()=>{
        if(deliverer.value.phone.length>10){
            phoneError.value='號碼必須是10個數字'
        }else if(!deliverer.value.phone.startsWith('09')){
            phoneError.value='號碼必須以09開頭'
        }else{
            phoneError.value="";
        }
    };
    const checkBirthday=()=>{
        const today = new Date();
        const birthday = new Date(deliverer.value.birthday);
        let age =today.getFullYear()-birthday.getFullYear();

        // 成年該年增減1歲的判斷:1.當年月份比較(不同月比大小) 2.當年月份比較(若同月比不同日)
        if(today.getMonth()<birthday.getMonth()||(today.getMonth()===birthday.getMonth() && today.getDate()<birthday.getDate())){
            age--;
        }
        if(age<18){
            birthdayError.value='年齡須符合成年';
        }else{
            birthdayError.value='';
        }
    };
    const checkEmail=()=>{
        let email =deliverer.value.email;

        if(!email.includes('@')){
            emailError.value='必須包含@符號'
        }else if(deliverer.value.email.length>30){
            emailError.value='Email長度錯誤請重新輸入'
        }else{
            emailError.value=''
        }
    };

  const addHandler = async () => {
        if(deliverer.value.name.length<2){
            alert('姓名至少請輸入兩個字');
            return;
        }

        if(deliverer.value.account.length<5){
            alert('帳號至少輸入5碼');
            return;
        }

        if(deliverer.value.password.length<8){
            alert('密碼至少輸入8碼');
            return;
        }

        if(deliverer.value.email.length<6){
            alert('Email至少輸入6碼');
            return;
        }

        if(nameError.value || accountError.value || passwordError.value || phoneError.value || birthdayError.value || emailError.value){
            alert('輸入資訊錯誤，請檢查紅字訊息');
            return;
        }

      const API_URL = `${import.meta.env.VITE_API_JAVAURL}deliverer`
      const response = await axios.post(API_URL, deliverer.value)
      if (response.data.success) {
          alert(response.data.message)
          router.push('/deliverer');

        //清空表單
        for(var i in deliverer.value){
            deliverer.value[i]='';
        }

        }else{
          console.log("fail");
      }
  }
  
  </script>
      
  <style scoped>
  </style>