# Sample Android Application for Avataar Integration

This repository contains sample code for integrating Avataar experience URL into your Android Application.

## Key Terms

- Product ID: This is the primary product identifier
- Variant ID: Sometimes products have multiple variants like color etc. If each such variant has a unique Product ID, then variant ID is the same as Product ID. If all variants have the same Product ID, then unique ID used to represent the variant should be used here.
- Parent App: The parent application invoking the Avataar's Web experience.

## Key Files

- MainActivity.java: This Activity triggers the AvataarWebViewActivity. The MainActivity passes on the productId and variantId to the AvataarWebViewActivity.
- SampleWebViewActivity: This triggers the Avataar experience URL. This takes the productID and variantID from the MainActivity above. This also creates the actual Android WebView object and JSBridge object below.
- SampleJSBridge: Sample JSBridge implementation. This implements the callback functions required for various functionalities like AddToCart etc. This implements the interface AvataarJSBridgeInterface.
- AvataarJSBridgeInterface: This is the interface of functions expected by Avataar's experience to power the callbacks from the Avataar URL.
- AvataarWebView: This class contains the boilerplate code to invoke the actual Avataar Web experience.

## Operation

- Avataar's Web experience might have buttons for functionalities like AddToCart. When the end-user clicks these buttons, the Parent App needs to be informed to take the necessary actions.
- AvataarJSBridgeInterface defines the callback functions required by Avataar's Web experience.
- The Parent App is expected to implement this interface and pass the object to AvataarWebView class. AvataarWebView class passes this to Avataar Web experience. In this example, SampleJSBridge implements the callback functions.
- In addition to this, product ID and variant ID are also needed for the Avataar Web experience. This is also passed during the open call to AvataarWebView object.
- In this example, the Avataar Web experience is triggered in a separate Activity - SampleWebViewActivity. This Activity controls the AvataarWebView object. This Activity is invoked by the MainActivity.
- The product and variant ID are passed from the MainActivity to SampleWebViewActivity to the AvataarWebView object.

## How to run

- Import into Android Studio
- Build and install on any Android device
