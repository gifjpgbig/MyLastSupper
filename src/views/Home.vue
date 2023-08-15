<template>
        <h2>Home Page</h2>
        <header class="bg-primary text-white py-3">
    <div class="container">
      <div class="row align-items-center">
        <div class="col">
          <button id="sidebarToggle" class="btn btn-secondary">
            <i class="fa fa-bars"></i> Toggle Sidebar
          </button>
        </div>
        <div class="col text-right">
          <!-- Other header content here -->
        </div>
      </div>
    </div>
  </header>
  
  <div class="container-fluid">
    <div class="row">
      <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
        <!-- Sidebar content here -->
      </nav>
      
      <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
        <!-- Main content here -->
      </main>
    </div>
  </div>
</template>
    
<script setup>
 $(document).ready(function() {
      $("#sidebarToggle").click(function() {
        $("#sidebar").toggleClass("active");
      });
    });
</script>
    
<style scoped>
     .sidebar {
    position: fixed;
    top: 0;
    bottom: 0;
    left: -250px; /* 初始情況下將側邊欄隱藏在畫面外 */
    width: 250px;
    padding-top: 20px;
    z-index: 1000;
    transition: all 0.3s;
  }

  .sidebar.active {
    left: 0; /* 顯示側邊欄 */
  }
</style>