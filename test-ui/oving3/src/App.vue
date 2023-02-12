<template>
  <body>
    <nav>
      <ul>
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="/calculator">Calculator</router-link></li>
        <li><router-link to="/contact">Contact</router-link></li>
      </ul>
    </nav>
    <router-view />
  </body>
</template>

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
  background: lightblue;
  height: 50px;
  min-width: 50%;
  margin-top: 5px;
  border-radius: 10px;
}

nav a {
  font-weight: bold;
  color: #36495c;
}

nav a.router-link-exact-active {
  color: #42b983;
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
}
</style>
