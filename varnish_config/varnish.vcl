backend default {
    .host = "127.0.0.1";
    .port = "8080";
}

sub vcl_fetch {
	if (beresp.status == 200) {
		set beresp.do_esi = true;
		if (beresp.ttl <= 0s || beresp.http.Set-Cookie || beresp.http.Vary == "*") {
			/*
			 * Mark as "Hit-For-Pass" for the next 2 minutes
			 */
			set beresp.ttl = 120 s;
			return (hit_for_pass);
		}
	}
	return (deliver);
}
