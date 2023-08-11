<template>
<nav class="navbar navbar-expand-lg navbar-light bg-light rounded" aria-label="Eleventh navbar example">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">EEIT Vue Demo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample09" aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExample09">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <RouterLink class="nav-link" to="/">首頁</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" to="/about">關於</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" to="/contact">聯絡我們</RouterLink>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown09" data-bs-toggle="dropdown" aria-expanded="false">訂單</a>
              <ul class="dropdown-menu" aria-labelledby="dropdown09">
                <li>
                  <RouterLink class="nav-link" to="/orders">客服訂單</RouterLink>
                </li>
                <li>
                  <RouterLink class="nav-link" to="/deliver">外送訂單</RouterLink>
                </li>
                <li><a class="dropdown-item" href="#">Something else here</a></li>
              </ul>
            </li>
          </ul>
          <form>
            <input class="form-control" type="text" placeholder="Search" aria-label="Search">
          </form>
          <ul class="navbar-nav mb-2 mb-lg-0">
            <RouterLink class="nav-link" to="/login">Login</RouterLink>
          </ul>
        </div>
      </div>
    </nav> 
</template>
    
<script setup>
   
</script>
    
<style>
    
</style>