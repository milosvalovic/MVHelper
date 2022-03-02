
# MVHelper
[![](https://jitpack.io/v/milosvalovic/MVHelper.svg)](https://jitpack.io/#milosvalovic/MVHelper)

Simple helper library to make apps easier.

## Installation

Step 1:
```java
    allprojects {
    		repositories {
    			...
    			maven { url 'https://jitpack.io' }
    		}
    	}
```

Step 2:
```java
    implementation 'com.github.milosvalovic:MVHelper:1.1'
```

## Usage

Kotlin:
```java
    MVHelper.dpToPx(20)
    // returns <Int>
```

```java
    MVHelper.Companion.dpToPx(20);
    // returns <int>
```