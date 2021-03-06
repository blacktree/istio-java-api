/*
 * *
 *  * Copyright (C) 2018 Red Hat, Inc.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *         http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

package me.snowdrop.istio.api.networking.v1beta1;

import io.fabric8.kubernetes.api.model.HasMetadata;
import me.snowdrop.istio.tests.BaseIstioTest;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GatewayTest extends BaseIstioTest {
	@Test
	public void checkBasicGateway() throws Exception {
		final Gateway gateway = new GatewayBuilder()
				.withNewMetadata()
				.withName("httpbin-gateway")
				.endMetadata()
				.withNewSpec()
				.addToSelector("istio", "ingressgateway")
				.addNewServer().withNewPort("http", 80, "HTTP").withHosts("httpbin.example.com").endServer()
				.addNewServer().withHosts("foobar.com").withNewPort("tls-0", 443, "TLS")
				.withNewTls().withMode(ServerTLSSettingsMode.PASSTHROUGH).withMinProtocolVersion(ServerTLSSettingsProtocol.TLSV1_2).endTls()
				.endServer()
				.endSpec()
				.build();

		final String output = mapper.writeValueAsString(gateway);
		Yaml parser = new Yaml();
		final Map<String, Map> reloaded = parser.loadAs(output, Map.class);

		assertEquals("Gateway", reloaded.get("kind"));

		final Map metadata = reloaded.get("metadata");
		assertNotNull(metadata);
		assertEquals("httpbin-gateway", metadata.get("name"));

		final Map<String, Map> spec = reloaded.get("spec");
		assertNotNull(spec);

		final Map<String, Map> selector = spec.get("selector");
		assertNotNull(selector);
		assertEquals("ingressgateway", selector.get("istio"));

		final List<Map> servers = (List) spec.get("servers");
		assertNotNull(servers);
		assertEquals(2, servers.size());

		Map<String, Map> server = servers.get(0);
		assertNotNull(server);

		Map<String, Map> port = server.get("port");
		assertNotNull(port);
		assertEquals(80, port.get("number"));
		assertEquals("http", port.get("name"));
		assertEquals("HTTP", port.get("protocol"));

		final List<Map> hosts = (List) server.get("hosts");
		assertNotNull(hosts);
		assertEquals("httpbin.example.com", hosts.get(0));

		server = servers.get(1);
		assertNotNull(server);
		port = server.get("port");
		assertNotNull(port);
		assertEquals(443, port.get("number"));
		assertEquals("tls-0", port.get("name"));
		assertEquals("TLS", port.get("protocol"));

		final Map<String, String> tls = server.get("tls");
		assertEquals("TLSV1_2", tls.get("minProtocolVersion"));
	}

	@Test
	public void roundtripBasicGatewayShouldWork() throws Exception {
		final Gateway gateway = new GatewayBuilder()
				.withNewMetadata()
				.withName("httpbin-gateway")
				.endMetadata()
				.withNewSpec()
				.addToSelector("istio", "ingressgateway")
				.addNewServer().withNewPort("http", 80, "HTTP").withHosts("httpbin.example.com").endServer()
				.addNewServer().withHosts("foobar.com").withNewPort("tls-0", 443, "TLS")
				.withNewTls().withMode(ServerTLSSettingsMode.PASSTHROUGH).withMinProtocolVersion(ServerTLSSettingsProtocol.TLSV1_2).endTls()
				.endServer()
				.endSpec()
				.build();

		final String output = mapper.writeValueAsString(gateway);

		HasMetadata reloaded = mapper.readValue(output, HasMetadata.class);

		assertEquals(gateway, reloaded);
	}
}
