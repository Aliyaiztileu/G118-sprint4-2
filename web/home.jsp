<%@ page import="kzbitlab.model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="kzbitlab.model.City" %><%--
  Created by IntelliJ IDEA.
  User: Yera
  Date: 06.10.2023
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>

</head>
<body>
<%@include file="navbar.jsp" %>
<div class="col-8 mx-auto">
    <h1 class="text-center">Welcome to BITLAB SHOP</h1>
    <h4 class="text-secondary text-center">Electronic devices with high quality and service</h4>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addItemModal">
        + ADD ITEM
    </button>

    <!-- Modal -->
    <form action="/add-item" method="post">
        <div class="modal fade" id="addItemModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="#addItemModal">ADD ITEM</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input type="text" name="item_name" placeholder="insert name..." class="form-control mt-1">
                        <input type="text" name="item_description" placeholder="insert description..."
                               class="form-control mt-1">
                        <input type="number" name="item_price" placeholder="insert price..." class="form-control mt-1">
                        <select name="item_city_id" class="form-select">

                            <%
                                List<City> cities = (List<City>) request.getAttribute("cities");
                                for (City city : cities) {
                            %>
                            <option value="<%=city.getId()%>"><%=city.getName() + " / " + city.getCode()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">ADD ITEM</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <div class="d-flex flex-wrap">
        <%
            List<Item> items = (List<Item>) request.getAttribute("items");
            for (Item item : items) {
                if (item != null) {
        %>
        <div class="card col-3 m-2">
            <div class="card-header">
                <%=item.getName()%>
            </div>
            <div class="card-body">
                <h5><%=item.getPrice()%>
                </h5>
                <p><%=item.getDescription()%>
                </p>
                <p><%=item.getCity().getName() + " / " + item.getCity().getCode()%>
                </p>
                <a href="#" class="btn btn-primary">BUY NOW</a> <br>


                <!-- Button trigger modal -->
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteItemModal">
                    DELETE ITEM
                </button>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editItemModal">
                    EDIT ITEM
                </button>

                <form action="/edit-item" method="post">
                    <div class="modal fade" id="editItemModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="#editItemModal">EDIT ITEM</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <input type="hidden" name="id" value="<%=item.getId()%>">

                                    <input type="text" name="item_name" class="form-control mt-1"
                                           value="<%=item.getName()%>">
                                    <input type="text" name="item_description" class="form-control mt-1"
                                           value="<%=item.getDescription()%>">
                                    <input type="number" name="item_price" class="form-control mt-1"
                                           value="<%=item.getPrice()%>">
                                    <select name="item_city_id" class="form-select">

                                        <%
                                            for (City city : cities) {
                                        %>
                                        <option value="<%=city.getId()%>"><%=city.getName() + " / " + city.getCode()%>
                                        </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <button type="submit" class="btn btn-primary">EDIT ITEM</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <!-- Modal -->
                <form action="/delete-item" method="post">
                    <div class="modal fade" id="deleteItemModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5">ARE YOU SURE? <%=item.getName()%>
                                    </h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <input type="hidden" name="id" value="<%=item.getId()%>">
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <button type="submit" class="btn btn-danger">Confirm delete</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <%
                }
            }
        %>
    </div>

</div>
</body>
</html>
