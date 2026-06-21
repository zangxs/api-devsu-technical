function fn() {
  var config = {
    clienteServiceUrl: 'http://localhost:8080/api/',
    cuentaServiceUrl: 'http://localhost:8081/api/'
  };

  karate.configure('retry', { count: 15, interval: 1000 });

  return config;
}