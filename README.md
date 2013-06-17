
Generate barcode images
-----------------------

Plugin page: [http://artifacts.griffon-framework.org/plugin/barcode](http://artifacts.griffon-framework.org/plugin/barcode)


Generates barcode images using [Barcode4j][1] and QRCode using [QRGen][2].

Usage
-----

The following nodes will become available on a View script upon installing this plugin

| *Node*  | *Property*        | *Type*           | *Required*  | *Bindable* | *Notes*                               | 
| ------- | ----------------- | ---------------- | ----------- | ---------- | ------------------------------------- |
| barcode | generator         | String           | yes         | no         | defaults to `codabar`                 |
|         | barcodeGenerator  | BarcodeGenerator | no          | no         | alternate way to register a generator |
|         | message           | String           | yes         | no         | the text to code                      |
| qrcode  | message           | String           | yes         | no         | the text to code                      |

Valid values for `generator` are: codabar, code39, postnet, intl2of5, ean-128, royal-mail-cbc, ean-13, datamatrix, code128, 
ean128, pdf417, upc-a, upc-e, usps4cb, ean-8

[1]: http://barcode4j.sourceforge.net/
[2]: http://kenglxn.github.com/QRGen/

