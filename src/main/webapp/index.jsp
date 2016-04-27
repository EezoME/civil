<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.04.2016
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="resources/global.css"/>
    <title>UI Prototype</title>
</head>
<body>
<header>
    <jsp:include page="partial/header.jsp"/>
</header>
<div class="content">
    <div class="content-title">
        <h2>Найпопулярніші проекти</h2>
        <h4>Проекти, які зібрали найбільшу кількість коштів</h4>
    </div>
    <table id="project-table" cellspacing="20">
        <tr>
            <td>
                <a href="project.html">
                    <img class="cover-image" src="resources/img/zoo.jpg" alt="zoo.jpg"/>
                </a>
                <div class="description">
                    <h3>Побудувати зоопарк</h3>
                    <p class="description-text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Fusce hendrerit enim eget sem sodales, at semper...
                    </p>
                    <p>
                        <span class="green-badge"><a href="category_link">Інфраструктура</a></span>
                        <span class="blue-badge"><a href="category_link">Тварини</a></span>
                    </p>
                </div>
                <div class="project-footer">
                    <div class="project-author">
                        <img class="userpic" src="http://placehold.it/50x50"/>
                        <div class="author-details">
                            <p>
                                <span><a class="username" href="user.html">Username</a></span>
                                <br>
                                <span class="add-user-info">Some additional info</span>
                            </p>
                        </div>
                    </div>
                    <div class="progressbar-wrapper">
                        <div class="progressbar" style="width: 45%"></div>
                    </div>
                    <div class="funds">
                        <div class="funded">12 345 &#8372;</div>
                        <div class="target">100 000 &#8372;</div>
                    </div>
                    <div class="exp-date">
                        <div class="created-at">02.02.2016</div>
                        <div class="time-left">Залишилось: <b>365 днів</b></div>
                    </div>
                </div>
                <div class="spacer"></div>
            </td>

            <td>
                <a href="project.html">
                    <img class="cover-image" src="resources/img/zoo.jpg" alt="zoo.jpg"/>
                </a>
                <div class="description">
                    <h3>Побудувати зоопарк</h3>
                    <p class="description-text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Fusce hendrerit enim eget sem sodales, at semper...
                    </p>
                    <p>
                        <span class="green-badge"><a href="category_link">Інфраструктура</a></span>
                        <span class="blue-badge"><a href="category_link">Тварини</a></span>
                    </p>
                </div>
                <div class="project-footer">
                    <div class="project-author">
                        <img class="userpic" src="http://placehold.it/50x50"/>
                        <div class="author-details">
                            <p>
                                <span><a class="username" href="user.html">Username</a></span>
                                <br>
                                <span class="add-user-info">Some additional info</span>
                            </p>
                        </div>
                    </div>
                    <div class="progressbar-wrapper">
                        <div class="progressbar" style="width: 45%"></div>
                    </div>
                    <div class="funds">
                        <div class="funded">12 345 &#8372;</div>
                        <div class="target">100 000 &#8372;</div>
                    </div>
                    <div class="exp-date">
                        <div class="created-at">02.02.2016</div>
                        <div class="time-left">Залишилось: <b>365 днів</b></div>
                    </div>
                </div>
                <div class="spacer"></div>
            </td>

            <td>
                <a href="project.html">
                    <img class="cover-image" src="resources/img/zoo.jpg" alt="zoo.jpg"/>
                </a>
                <div class="description">
                    <h3>Побудувати зоопарк</h3>
                    <p class="description-text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Fusce hendrerit enim eget sem sodales, at semper...
                    </p>
                    <p>
                        <span class="green-badge"><a href="category_link">Інфраструктура</a></span>
                        <span class="blue-badge"><a href="category_link">Тварини</a></span>
                    </p>
                </div>
                <div class="project-footer">
                    <div class="project-author">
                        <img class="userpic" src="http://placehold.it/50x50"/>
                        <div class="author-details">
                            <p>
                                <span><a class="username" href="user.html">Username</a></span>
                                <br>
                                <span class="add-user-info">Some additional info</span>
                            </p>
                        </div>
                    </div>
                    <div class="progressbar-wrapper">
                        <div class="progressbar" style="width: 45%"></div>
                    </div>
                    <div class="funds">
                        <div class="funded">12 345 &#8372;</div>
                        <div class="target">100 000 &#8372;</div>
                    </div>
                    <div class="exp-date">
                        <div class="created-at">02.02.2016</div>
                        <div class="time-left">Залишилось: <b>365 днів</b></div>
                    </div>
                </div>
                <div class="spacer"></div>
            </td>
        </tr>

        <tr>
            <td>
                <a href="project.html">
                    <img class="cover-image" src="resources/img/zoo.jpg" alt="zoo.jpg"/>
                </a>
                <div class="description">
                    <h3>Побудувати зоопарк</h3>
                    <p class="description-text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Fusce hendrerit enim eget sem sodales, at semper...
                    </p>
                    <p>
                        <span class="green-badge"><a href="category_link">Інфраструктура</a></span>
                        <span class="blue-badge"><a href="category_link">Тварини</a></span>
                    </p>
                </div>
                <div class="project-footer">
                    <div class="project-author">
                        <img class="userpic" src="http://placehold.it/50x50"/>
                        <div class="author-details">
                            <p>
                                <span><a class="username" href="user.html">Username</a></span>
                                <br>
                                <span class="add-user-info">Some additional info</span>
                            </p>
                        </div>
                    </div>
                    <div class="progressbar-wrapper">
                        <div class="progressbar" style="width: 45%"></div>
                    </div>
                    <div class="funds">
                        <div class="funded">12 345 &#8372;</div>
                        <div class="target">100 000 &#8372;</div>
                    </div>
                    <div class="exp-date">
                        <div class="created-at">02.02.2016</div>
                        <div class="time-left">Залишилось: <b>365 днів</b></div>
                    </div>
                </div>
                <div class="spacer"></div>
            </td>

            <td>
                <a href="project.html">
                    <img class="cover-image" src="resources/img/zoo.jpg" alt="zoo.jpg"/>
                </a>
                <div class="description">
                    <h3>Побудувати зоопарк</h3>
                    <p class="description-text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Fusce hendrerit enim eget sem sodales, at semper...
                    </p>
                    <p>
                        <span class="green-badge"><a href="category_link">Інфраструктура</a></span>
                        <span class="blue-badge"><a href="category_link">Тварини</a></span>
                    </p>
                </div>
                <div class="project-footer">
                    <div class="project-author">
                        <img class="userpic" src="http://placehold.it/50x50"/>
                        <div class="author-details">
                            <p>
                                <span><a class="username" href="user.html">Username</a></span>
                                <br>
                                <span class="add-user-info">Some additional info</span>
                            </p>
                        </div>
                    </div>
                    <div class="progressbar-wrapper">
                        <div class="progressbar" style="width: 45%"></div>
                    </div>
                    <div class="funds">
                        <div class="funded">12 345 &#8372;</div>
                        <div class="target">100 000 &#8372;</div>
                    </div>
                    <div class="exp-date">
                        <div class="created-at">02.02.2016</div>
                        <div class="time-left">Залишилось: <b>365 днів</b></div>
                    </div>
                </div>
                <div class="spacer"></div>
            </td>

            <td>
                <a href="project.html">
                    <img class="cover-image" src="resources/img/zoo.jpg" alt="zoo.jpg"/>
                </a>
                <div class="description">
                    <h3>Побудувати зоопарк</h3>
                    <p class="description-text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Fusce hendrerit enim eget sem sodales, at semper...
                    </p>
                    <p>
                        <span class="green-badge"><a href="category_link">Інфраструктура</a></span>
                        <span class="blue-badge"><a href="category_link">Тварини</a></span>
                    </p>
                </div>
                <div class="project-footer">
                    <div class="project-author">
                        <img class="userpic" src="http://placehold.it/50x50"/>
                        <div class="author-details">
                            <p>
                                <span><a class="username" href="user.html">Username</a></span>
                                <br>
                                <span class="add-user-info">Some additional info</span>
                            </p>
                        </div>
                    </div>
                    <div class="progressbar-wrapper">
                        <div class="progressbar" style="width: 45%"></div>
                    </div>
                    <div class="funds">
                        <div class="funded">12 345 &#8372;</div>
                        <div class="target">100 000 &#8372;</div>
                    </div>
                    <div class="exp-date">
                        <div class="created-at">02.02.2016</div>
                        <div class="time-left">Залишилось: <b>365 днів</b></div>
                    </div>
                </div>
                <div class="spacer"></div>
            </td>
        </tr>

        <tr>
            <td>
                <a href="project.html">
                    <img class="cover-image" src="resources/img/zoo.jpg" alt="zoo.jpg"/>
                </a>
                <div class="description">
                    <h3>Побудувати зоопарк</h3>
                    <p class="description-text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Fusce hendrerit enim eget sem sodales, at semper...
                    </p>
                    <p>
                        <span class="green-badge"><a href="category_link">Інфраструктура</a></span>
                        <span class="blue-badge"><a href="category_link">Тварини</a></span>
                    </p>
                </div>
                <div class="project-footer">
                    <div class="project-author">
                        <img class="userpic" src="http://placehold.it/50x50"/>
                        <div class="author-details">
                            <p>
                                <span><a class="username" href="user.html">Username</a></span>
                                <br>
                                <span class="add-user-info">Some additional info</span>
                            </p>
                        </div>
                    </div>
                    <div class="progressbar-wrapper">
                        <div class="progressbar" style="width: 45%"></div>
                    </div>
                    <div class="funds">
                        <div class="funded">12 345 &#8372;</div>
                        <div class="target">100 000 &#8372;</div>
                    </div>
                    <div class="exp-date">
                        <div class="created-at">02.02.2016</div>
                        <div class="time-left">Залишилось: <b>365 днів</b></div>
                    </div>
                </div>
                <div class="spacer"></div>
            </td>

            <td>
                <a href="project.html">
                    <img class="cover-image" src="resources/img/zoo.jpg" alt="zoo.jpg"/>
                </a>
                <div class="description">
                    <h3>Побудувати зоопарк</h3>
                    <p class="description-text">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Fusce hendrerit enim eget sem sodales, at semper...
                    </p>
                    <p>
                        <span class="green-badge"><a href="category_link">Інфраструктура</a></span>
                        <span class="blue-badge"><a href="category_link">Тварини</a></span>
                    </p>
                </div>
                <div class="project-footer">
                    <div class="project-author">
                        <img class="userpic" src="http://placehold.it/50x50"/>
                        <div class="author-details">
                            <p>
                                <span><a class="username" href="user.html">Username</a></span>
                                <br>
                                <span class="add-user-info">Some additional info</span>
                            </p>
                        </div>
                    </div>
                    <div class="progressbar-wrapper">
                        <div class="progressbar" style="width: 45%"></div>
                        </p>
                        <p>
                            <span class="green-badge"><a href="category_link">Інфраструктура</a></span>
                            <span class="blue-badge"><a href="category_link">Тварини</a></span>
                        </p>
                    </div>
                    <div class="project-footer">
                        <div class="project-author">
                            <img class="userpic" src="http://placehold.it/50x50"/>
                            <div class="author-details">
                                <p>
                                    <span><a class="username" href="user.html">Username</a></span>
                                    <br>
                                    <span class="add-user-info">Some additional info</span>
                                </p>
                            </div>
                        </div>
                        <div class="progressbar-wrapper">
                            <div class="progressbar" style="width: 45%"></div>
                        </div>
                        <div class="funds">
                            <div class="funded">12 345 &#8372;</div>
                            <div class="target">100 000 &#8372;</div>
                        </div>
                        <div class="exp-date">
                            <div class="created-at">02.02.2016</div>
                            <div class="time-left">Залишилось: <b>365 днів</b></div>
                        </div>
                    </div>
                    <div class="spacer"></div>
                </div>
            </td>
        </tr>
    </table>
    <div id="page-navigator">
        <a class="page-item" href="#">1</a>
        <a class="page-item page-item-active" href="#">2</a>
        <a class="page-item" href="#">3</a>
        <span>...</span>
        <a class="page-item" href="#">123</a>
        <a class="page-item" href="#">Далі >></a>
    </div>
</div>
<footer>
    <div id="footer">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
        Fusce hendrerit enim eget sem sodales, at semper...
    </div>

</footer>
</body>
</html>