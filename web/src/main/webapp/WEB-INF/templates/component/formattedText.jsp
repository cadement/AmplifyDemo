<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="cmsfn" uri="http://magnolia-cms.com/taglib/templating-components/cmsfn" %>

<div id="component" style="word-wrap: break-word;">
    <c:if test="${not empty content.title}"><h3>${content.title}</h3></c:if>
    <div>
        ${cmsfn:decode(content).body}
    </div>
</div>