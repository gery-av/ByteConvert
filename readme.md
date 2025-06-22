# ByteConvert

ByteConvert is a simple Java application that converts any binary file into a C++ header file (`.hpp`) containing a byte array. It is suitable for embedding binary files (such as images, fonts, etc.) into C++ source code.

## How to Use ?

1. Download this repository (or clone it using `git clone`).
2. Make sure you have Java installed.
3. Run the app:
   ```sh
   java app
   ```
4. Select the binary file you want to convert when prompted.
5. The `.hpp` file will be generated in the same folder as your source

## Example Output

If you select `logo.png`, the generated `logo_png.hpp` will look like:
```cpp
#pragma once

// Generated from: logo.png
// Thank you for using ByteConvert. <3 

unsigned char logo_png[] = {
    0x89, 0x50, 0x4E, 0x47, ...
};
unsigned int logo_png_len = 12345;
```