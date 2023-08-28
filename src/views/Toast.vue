<!-- <template>
    <div id="app">
      <button @click="showToast">Show Toast</button>
      <span>Counter is at {{ counter }}</span>
    </div>
  </template>
  
  <script>
  import MyToastComponent from '../components/MyToastComponent.vue';
  
  export default {
    name: "App",
    data: () => ({
      counter: 0
    }),
    methods: {
      showToast() {
        // Define the content object with the component, props and listeners
        const content = {
          component: MyToastComponent,
          // Any prop can be passed, but don't expect them to be reactive
          props: {
            counter: this.counter
          },
          // Listen and react to events using callbacks. In this case we listen for
          // the "click" event emitted when clicking the toast button
          listeners: {
            click: () => {
              this.counter++;
              this.$toast.success(`Toast with counter ${this.counter}`, { position: "top-left" })
            }
          }
        }
  
        // Render the toast and its contents
        this.$toast(content, { position: "bottom-right" });
      }
    }
  };
  </script>
  
  <style>
  #app {
    font-family: "Avenir", Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
  </style>
   -->


   <template>
    <div id="app">
      <button @click="showToast">Show toast</button>
    </div>
  </template>
  
  <script>
  export default {
    name: "App",
    methods: {
      showToast() {
        this.$toast.success("Hello!");
      }
    }
  };
  </script>
  
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
  