/**
 * 
 */
package com.dgtl.datamanagement.util.dialogFlow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import com.google.auth.Credentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

/**
 * @author A631114
 *
 */

public class DFConfig {

	// To be read from environment variables
//	private static String PROJECT_ID = "claimprocessor-kbfbfa"; // System.getenv("DIALOGFLOW_PROJECT_ID");
//	private static String DIALOGFLOW_PRIVATE_KEY_ID = "547a9ff1ceaad9ca9601f25b226915cae425e47c";
//	private static String DIALOGFLOW_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCTi1HPtkmInd6x\nvy6Ksgq9wwkGSVMbLWZFrfoVYzV77WkJtoJpPIJGPvg64MXvyfiyMjkuwT566pCL\nnUtc/PZUX98MZ68EAeYyDrKwnra3gNbUP/iASBtyGIKAHtBkRdezdrwZigsQXhv8\nJz94BwvL5eVG1yXXkjAycheLxTK4wn9uvwsXDtrAOC5azoHHI6Vs4ukDooJQrvJ9\nQWVGZHuXiEHo8kErXdVgeqphgS0ULxfznCDQgV5MFDaCzSlyAiCMNhkkq5WbaIOK\nkm32R6cjJKgfTXo6hysl13Qb6hbxtKJEQq8ZUfvaB12F+3amOg9PMNa1bhoU9NQO\n+PjTdxwjAgMBAAECggEAAgHNqZkts+WN33Yd0HYujjlGeKbrsf1ynDULJ1pTom1u\n1dp9OKhBj2oQ32AqYSIqt2XWgEvRqUNilvubSFcKlMg/FQCeEVxjQMARbzShgKqI\ngZejLj+M3Ab4U3zX0d1fW0ocse4+S0k0cUApbV0PLIViko2frqiG7lC+AEE8+OVr\nIZEFfChVa/RxIxx+MVGmkLT6wJTJ/TvZRG+cjOqIuII/ArbxdlKfjXIwra2bka5R\nYEkYpx9XWfyNICZECiCE4Ric0QQDHwNaeEhjUI/was1PxLgNsrBp6Ap6WfIUo7yN\nf/Y/CT+OZIj6/8gF66LjI50C1H6qQ9MXU5PWIN2m6QKBgQDNs+Y4d2TkzE9EiYwG\n94P8+8dYt9tDE7BpAkc+MVSfq0Rolg6JRrf6Rhh6QPbQeyEEg72DOHQcAJ0b0uXs\ngbBh+fPoYjd9EhbcJTwSg/8JH9qED5+D6ElCS4UwqgxGNI6XFMw0rpL09AHJxvWn\n/a6iSipRxDUWnQufWCPPT8EbOwKBgQC3nvLwb8LG63pU6Vft3wgSlkgVqaqnnPN7\nLzPgAMsLuN+gMN/vPOyIPQ9Ov0F/Hgp4V8GhaGKyDFGZHzUlkPdukK55OtI+bw0s\nBeYNESbh2ypjzRxBDkwYrWog3UhTcpOGqJJ5/OHgP/AslPKnRayoLHdjV+HuOMci\nbmwluS9kOQKBgQDIqkJfuzkVaam6NmP/N93uIL+q8FBY8xAYoDxXk0/2IuEtFZVA\nnVhEMbUXLeCYKEVhXhK1u5Co8IBS8D2TpkAmk+s4OvfebpMWVsxvU4yrngNQ6Ny3\nlRZCMeHQQ1KN+h9Pit5aViLOCmrkJ87k/jgp4Se/dCRF1xXLb/Qe/LJomQKBgQCt\nYhZtuGpIILQLl6aigxYBTwxUADiXOpmiOMkX/Ee+Etx+S4g4Q+PpJRbeObQjzv79\n8Ydx3LpM+1rVZmVtynwJV+6XpFZAA+aBKjS3I/n+DE/zmwrR1yk9qAntbGVN/oOV\nBHoDXCkA8f7caezSy/SjfBFjqek5Yz2NoozV9g3s6QKBgQCy0QfQqgTKZnP3NqZK\nEJbmKfcAIftaA+cymenA2PrIzJdTtWgAmxvZrRf+iZudiwjMlKGWMCvfMbzky2yu\nFFCEc/E2H3AhsZ+Babuq64J+qAiiMjkHAvARGkAxIvW7h8S2hGMx3bMVDvYHWiaP\n8JrlJWPtxcCfGv5rAhQjz9xWRA==\n-----END PRIVATE KEY-----\n";
//	private static String DIALOGFLOW_CLIENT_EMAIL = "dmsintegrator@claimprocessor-kbfbfa.iam.gserviceaccount.com";
//	private static String CLIENTID = "102668702762387616434";
//	private static String TOKEN_SERVER_URI = "https://oauth2.googleapis.com/token";
	
	private static String PROJECT_ID = "texttalkagent-kafobl"; // System.getenv("DIALOGFLOW_PROJECT_ID");
	private static String DIALOGFLOW_PRIVATE_KEY_ID = "2ca0c53f33f01211f80b02276278384325dd7cf3";
	private static String DIALOGFLOW_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDPcRae493QI6pn\n/q9CUNGqWO54cEn2bbZ+rBCMO7uReqNMQtrJSxkemUADP6XiH1yEwqiX6t8h/HFo\naUflX07SohN8sUjK7u2nuiVh1Iogqd8pVaSuPreA9eMgwQWGgYycjQ+prlSwwNgY\nDz8+GQWMfCsaokUgHmSpStQ4+0L9LsX9sVttr5/hCOArm+ja7VOn5xh3i+O2WheH\nCjrJOhIvVfMpNYS6iLXRtAWGMCEdpEVaKM5xz66NLS+cNUW7bw1nCvJ3+ux2fPo6\nQ/HJCXLXrHAV0dCytHlk8NQDueClRfkBvkE1QxiQo8vQ1LNMc266NuMUgeIL9uY3\nM3+uEHgTAgMBAAECggEABpuS3Zo+jULhAW0HRrTJMhbqQJFNYvlrZuQggj3kSBOc\nIE0JcRKXdq0OJ/DoFM0jOSthnf99tX2TgO5au3+O+6LtKyATH/KRRNaeGoDyPNqw\n8vP5Se1RyaWwB7rr/kdGbTWb8R1OVipwrpJe530MRFfB6XO0XNdot3Du5oJUtYHU\nzOL849bpkDBF/Fmnbv6S6IoGcP/8wCQJguA0K0Z9vIFHKh4Aar4/Y2K55KVrGExS\n7/QUdIz/5JgKNYgSNLgeN8aflm/7ei1vzkAEpxhKuKfBVW+ytNvZ14JMcQSksnMC\nfZlDJyByiNhhrm8hBGlzP2AzxIZklSfQq8IXegCfWQKBgQDnIsrYIkGEM0BuvymA\npTtUaXVg9z7xp5yM/qC82/xU7dDHf14W9ahKoC6l0oScB0Em8t5HvVSIkDQURr+0\nhUf/HOsTZ+oqh4+cghNxIQgcu6jvK1aOpjrq24bGxckf8DPvTg/QdIeF0PTFnWfr\nQLU/kt3BJHSKrj71ejTpFEoIfQKBgQDlwcmDCRf9V3ibUmNnD8Q6deqZDPmsymie\nTfhDbP5obl2919e2sghc1ozWbr+cSCiqSniIWDCI3FgU/fjHcKAHoMGTkUQCkzXK\nTa4fiVq4tfqs7zCp4i/AnQ2PMQTPvpWBVQp6le5dxYKaOJTGEewW+1a3BRKDexnf\nlleoyn/3zwKBgQDUIi2TgpUo9TT8r7wykvPJ/GQ7k5CUj+XxzbzOuQR1GjNU9gPw\nBJTpsOw/SuBkp4/XZG4333+XOuphdbF6yByU+wCRtfzH/dUDqqqXWN6uKFna8Kan\nk89dQBAJnPuDsZyo1LdU9a5wVaBhQp9e3eu7plqaao//u/6SpA+tn+52rQKBgEnH\nWeNXJzn2Pcd6xGrS+EthlC0DqqVe9gtntEJKnMOCNV4CEsYbE25kgyNUuvj/fvd4\nJI5761H3l91d1OaOdwUcDS1K55EAHrTRDwoXfGd2IIIp6PGUrYrno/QIZKglZjL6\n0Puea8X258KIStXz6WFT4nuxCEnqXNuclpegN0mnAoGASAuo5LIYjq0TeprDcRH7\nH2pa9A2vtXiYcooBgrUZqhc8teCmWXvr0TAJ7AUP3PclAjse0U0lCL53Lc41uHpG\n7O7bGG7WWUAYLX2fynaOkXn60TrD7MnuHhCryNDQQRHv9oQfIRatCuGkijzCDcmG\nQKf0qVEXCI+oHjspiO0Z1IA=\n-----END PRIVATE KEY-----\n";
	private static String DIALOGFLOW_CLIENT_EMAIL = "texttalkagent-kafobl@appspot.gserviceaccount.com";
	private static String CLIENTID = "112367114597538116957";
	private static String TOKEN_SERVER_URI = "https://oauth2.googleapis.com/token";
	
	public static String getProjectID() {
		return PROJECT_ID;
	}

	public static Credentials getCredentails() throws GeneralSecurityException {
		try {
			// System.out.println("ProjectID is >>>>>>"+System.getenv("DIALOGFLOW_PROJECT_ID"));
			StringBuilder pkcs8Lines = new StringBuilder();
			
			BufferedReader rdr = new BufferedReader(new StringReader(DIALOGFLOW_PRIVATE_KEY));

			String line;
			while ((line = rdr.readLine()) != null) {
				pkcs8Lines.append(line);
			}

			// Remove the "BEGIN" and "END" lines, as well as any whitespace
			String pkcs8Pem = pkcs8Lines.toString().replace("-----BEGIN PRIVATE KEY-----", "")
					.replace("-----END PRIVATE KEY-----", "").replaceAll("\\s+", "");

			PrivateKey privKey = KeyFactory.getInstance("RSA")
					.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(pkcs8Pem)));

			return ServiceAccountCredentials.newBuilder().setProjectId(PROJECT_ID)
					.setPrivateKeyId(DIALOGFLOW_PRIVATE_KEY_ID).setPrivateKey(privKey)
					.setClientEmail(DIALOGFLOW_CLIENT_EMAIL).setClientId(CLIENTID)
					.setTokenServerUri(URI.create(TOKEN_SERVER_URI)).build();

		} catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
			throw new GeneralSecurityException(e);
		}
	}
}
