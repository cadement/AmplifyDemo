<%@taglib prefix="cmsfn" uri="http://magnolia-cms.com/taglib/templating-components/cmsfn" %>

<div id="component" style="word-wrap: break-word;">
    <h4>in ${article.category}</h4>
    <div>
        ${cmsfn:decode(content).body}
    </div>
</div>