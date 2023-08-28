<template>
  <aside class="sidebar">
    <div class="container-fluid bg-dark">
      <div class="row row flex-nowrap">
        <div class="col col-auto col-md-3 col-xl-2 px-sm-2 px-0">
          <div
            class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100"
          >
            <!-- <a
              class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none"
              href="/"
              ><span class="fs-5 d-none d-sm-inline">Menu</span></a
            > -->
            <ul
              id="menu"
              class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start"
            >
              <li class="nav-item">
                <!-- <a class="nav-link align-middle px-0" href="#">
                            <i class="fa fa-home fs-4 bi-house"></i>
                            <span class="ms-1 d-none d-sm-inline">首頁</span></a> -->

                <RouterLink class="nav-link align-middle px-0" to="/">
                  <i class="fa fa-home fs-4 bi-house"></i>
                  <span class="d-none d-sm-inline">首頁</span></RouterLink
                >
              </li>

              <li class="nav-item">
                <!-- <a class="nav-link px-0 align-middle" href="#"><i class="fa fa-table fs-4 bi-table"></i><span class="ms-1 d-none d-sm-inline">Orders</span></a> -->
                <RouterLink class="nav-link align-middle px-0" to="/about">
                  <i class="fa fa-table fs-4 bi-table"></i>
                  <span class="d-none d-sm-inline">績效查核</span></RouterLink
                >
              </li>
              <li class="nav-item">
                <a
                  class="nav-link px-0 align-middle dropdown-toggle"
                  href="#submenu1"
                  data-bs-toggle="collapse"
                  role="button"
                  aria-expanded="false"
                  aria-controls="submenu1"
                  ><i class="fa fa-bar-chart fs-4 bi-speedometer2"></i
                  ><span class="ms-1 d-none d-sm-inline">客服訂單管理</span></a
                >
                <ul
                  id="submenu1"
                  class="nav flex-column ms-1 collapse ms-1 ms-md-3"
                >
                <li class="w-100">
                    <!-- <a class="nav-link px-0" href="#"><span class="d-none d-sm-inline">Product</span></a> -->
                    <RouterLink class="nav-link" to="/orders"
                      >客服訂單</RouterLink
                    >
                  </li>
                  <li>
                    <!-- <a class="nav-link px-0" href="#"><span class="d-none d-sm-inline">Product</span></a> -->
                    <RouterLink class="nav-link" to="/deliver"
                      >外送訂單</RouterLink
                    >
                  </li>
                </ul>
              </li>
              <li>
                <a
                  class="nav-link px-0 align-middle"
                  href="#submenu3"
                  data-bs-toggle="collapse"
                  ><i class="fa fa-th-large fs-4 bi-grid"></i
                  ><span class="ms-1 d-none d-sm-inline">外送員管理</span></a
                >
                <ul
                  id="submenu3"
                  class="collapse nav flex-column ms-1"
                  data-bs-parent="#menu"
                >
                  <li class="w-100">
                    <!-- <a class="nav-link px-0" href="#"><span class="d-none d-sm-inline">Product</span></a> -->
                    <RouterLink class="nav-link" to="/orders"
                      >客服訂單</RouterLink
                    >
                  </li>
                  <li>
                    <!-- <a class="nav-link px-0" href="#"><span class="d-none d-sm-inline">Product</span></a> -->
                    <RouterLink class="nav-link" to="/deliver"
                      >外送訂單</RouterLink
                    >
                  </li>
                </ul>
              </li>
              <li>
                <a class="nav-link px-0 align-middle" href="#"
                  ><i class="fa fa-users fs-4 bi-people"></i
                  ><span class="ms-1 d-none d-sm-inline">即時訊息</span></a
                >
              </li>

              <li class="nav-item">
                <!-- <a class="nav-link px-0 align-middle" href="#"><i class="fa fa-table fs-4 bi-table"></i><span class="ms-1 d-none d-sm-inline">Orders</span></a> -->
                <RouterLink class="nav-link align-middle px-0" to="/contact">
                  <i class="fa fa-table fs-4 bi-people"></i>
                  <span class="d-none d-sm-inline">關於我們</span></RouterLink
                >
              </li>
              <li class="nav-item">
                <RouterLink class="nav-link align-middle px-0" to="/toast">
                  <i class="fa fa-table fs-4 bi-people"></i>
                  <span class="d-none d-sm-inline">吐司測試</span></RouterLink
                >
              </li>
            </ul>
            <hr />
            <div class="dropdown pb-4">
              <a
                id="dropdownUser1"
                class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
                href="#"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                ><img
                  class="rounded-circle"
                  src="User.png"
                  alt="hugenerd"
                  width="30"
                  height="30"
                /><span class="d-none d-sm-inline mx-1">loser</span></a
              >
              <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                <li><a class="dropdown-item" href="#">New project...</a></li>
                <li><a class="dropdown-item" href="#">Settings</a></li>
                <li><a class="dropdown-item" href="#">Profile</a></li>
                <li>
                  <hr class="dropdown-divider" />
                </li>
                <li><a class="dropdown-item" href="#">Sign out</a></li>
              </ul>
            </div>
          </div>
        </div>
        <!-- <p class="col py-3"><br /> Content area...<br /></p> -->
      </div>
    </div>
  </aside>
</template>

<script setup></script>

<style scoped>
.sidebar {
  position: fixed;
  top: 71.98px;
  left: 0;
  width: 250px;
  height: 100%;
  overflow-y: auto;
}

.sidebar > .container-fluid {
  background-color: #343a40; /* 設定背景顏色 */
  color: #fff; /* 設定文本顏色 */
}

/* 如果需要微調內部元素的樣式，可以進一步指定其類別或元素名稱 */
.sidebar a {
  color: #fff; /* 設定連結的文本顏色 */
}

/* .dropdown-toggle {
  display: none;
} */

/*
ul#menu {
  max-width: 100%; 
  width: auto; 
}
*/

</style>
