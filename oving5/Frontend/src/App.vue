<template>
  <body>
    <nav class="h-20 min-w-[700px]">
      <ul>
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="/calculator">Calculator</router-link></li>
        <li><router-link to="/contact">Contact</router-link></li>
      </ul>
    </nav>
    <router-view />
  </body>
</template>

<script></script>

<style>
body {
  display: flex;
  margin: 0;
  padding: 0;
  min-height: 500px;
  flex-direction: column;
}

nav {
  padding: 30px;
  display: flex;
  align-self: center;
  align-items: center;
  justify-content: center;
  background: #dfbf9f;
  height: 50px;
  min-width: 50%;
  margin-top: 5px;
  border-radius: 10px;
}

nav a {
  font-weight: bold;
  color: #cc9966;
}

nav a.router-link-exact-active {
  color: #9f7245;
}

ul {
  display: flex;
  justify-content: center;
  margin: 0;
  padding: 0 10px;
  height: 100%;
}

li {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-content: center;
  list-style: none;
  margin: 10px;
  padding: 5px;
  font-family: "Courier New", Courier, monospace;
  font-size: 30px;
}
</style>
