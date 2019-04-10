# JWToken Java

An implementation of [JSON Web Tokens](https://tools.ietf.org/html/rfc7519).

## Introduction

JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret (with the HMAC algorithm).

## Usage

### JWToken.sign(claim, secret, algorithm)

`return Token`

`claim` is a Claim object containing the claim.

`secret` is a string containing the secret for HMAC algorithms.

`algorithm` is the algoritm used for the Hmac

## Available Algorithms

The library implements JWT Verification and Signing using the following algorithms:

| JWS | Algorithm | Description |
| :-------------: | :-------------: | :----- |
| HS256 | HMAC256 | HMAC with SHA-256 |
| HS384 | HMAC384 | HMAC with SHA-384 |
| HS512 | HMAC512 | HMAC with SHA-512 |

The specs defines many more algorithms for signing. You can find them all in [RFC 7518](https://tools.ietf.org/html/rfc7518#section-3).

**Example**

```java
Claim claim = new Claim("iss", "sub", "aud", new Date(new Date().getTime() + 1000), new Date());

String secret = "s3cr3t";

Token token = JWToken.sign(claim, secret, Algorithm.HS256);
System.out.println(token.getToken());
```

### JWToken.verify(token, secret)

`return Claim`

`token` is a Token object.

`secret` is a string containing the secret for HMAC algorithms.

**Example**

```java
Token token = new Token("xxxxx.yyyyyy.zzzzzz");
String secret = "s3cr3t";
String decodedPayload = JWToken.verify(token, secret);
System.out.println(decodedPayload.toJson());
```

# Licence

MIT License

Copyright (c) 2019 Yassine Fathi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.