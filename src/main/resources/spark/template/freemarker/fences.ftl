<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Geofencer">
    <h2>${pageTitle}</h2>
    <div class="geobox">
        <h3>Add fences:</h3>
        <form action="/submit" method="post">
            <dl>
              <dt>Name:
              <dd><input type="text" name="name" maxlength="50" size="30" value="${name!}">
              <dt>Type:
              <dd><input type="text" name="type" maxlength="50" size="30" value="${type!}">
              <dt>North:
              <dd><input type="text" name="north" maxlength="20" size="30" value=${north!}>
              <dt>South:
              <dd><input type="text" name="south" maxlength="20" size="30" value=${south!}>
              <dt>East:
              <dd><input type="text" name="east" maxlength="20" size="30" value=${east!}>
              <dt>West:
              <dd><input type="text" name="west" maxlength="20" size="30" value=${west!}>
            </dl>
            <div class="actions"><input type="submit" value="Submit"></div>
        </form>
    </div>
    <br/>
    <div class="geobox">
        <h3>Filter fences:</h3>
        <form action="/match" method="post">
            <dl>
              <dt>Latitude:
              <dd><input type="text" name="latitude" maxlength="50" size="30" value="${latitude!}">
              <dt>Longitude:
              <dd><input type="text" name="longitude" maxlength="50" size="30" value="${longitude!}">
              <dt>Radius:
              <dd><input type="text" name="radius" maxlength="20" size="30" value=${radius!}>
            </dl>
            <div class="actions"><input type="submit" value="Submit"></div>
        </form>
    </div>
    <ul class="messages">
    <#if fences??>
    <#list fences as fence>
		<li><p>
		<strong>${fence.name}</strong>
		${fence.type}
		<small>North: ${fence.north}</small>
		<small>South: ${fence.south}</small>
		<small>East: ${fence.east}</small>
		<small>West: ${fence.west}</small>

	<#else>
		<li><em>No fences.</em>
	</#list>
	<#else>
		<li><em>No fences.</em>
	</#if>
	</ul>
</@layout.masterTemplate>