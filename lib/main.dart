import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MaterialApp(title: "Neon Widgets", home: MyApp()));
}

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyAppState();
  }
}

class MyAppState extends State<MyApp> {
  void startServiceInPlatform() async {
    if (Platform.isAndroid) {
      const methodChannel = MethodChannel("samples.flutter.dev/battery");
      int result = await methodChannel.invokeMethod("getBatteryLevel");
      print(result);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      body: Center(
        child: InkWell(
          onTap: startServiceInPlatform,
          child: Container(
            child: const Text("TAP HERE"),
            color: Colors.green,
          ),
        ),
      ),
    );
  }
}
