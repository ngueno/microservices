package com.ngueno.rest.webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ngueno.rest.webservices.app.entities.Name;
import com.ngueno.rest.webservices.app.entities.PersonV1;
import com.ngueno.rest.webservices.app.entities.PersonV2;

/**
 * @author Norton Gueno
 *
 *         Versioning can be done in a few ways, it will depends on the
 *         necessity of the company
 *
 *         Things to keep in mind during to make a choice around the versioning:
 * 
 *         - URI Pollution - Misuse of HTTP Headers (headers are not intended to
 *         perform versioning) - Caching (using headers we cannot have cashing)
 *         - If the request will be executed in a browser - API Documentation
 * 
 *         So it will depends on the business needs :)
 *
 */
@RestController
public class PersonVersioningController {

	@GetMapping("v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Norton Gueno");
	}

	@GetMapping("v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Norton", "Gueno"));
	}

	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 getPersonParamV1() {
		return new PersonV1("Norton Gueno");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 getPersonParamV2() {
		return new PersonV2(new Name("Norton", "Gueno"));
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonHeaderV1() {
		return new PersonV1("Norton Gueno");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonHeaderV2() {
		return new PersonV2(new Name("Norton", "Gueno"));
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonProducesV1() {
		return new PersonV1("Norton Gueno");
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonProducesV2() {
		return new PersonV2(new Name("Norton", "Gueno"));
	}
}
