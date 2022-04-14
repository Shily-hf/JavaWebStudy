left.html没有样式，同时数据也不展示，原因是：我们直接去请求的静态页面资源，那么并没有执行super.processTemplate(methodReturnStr, request, response);
