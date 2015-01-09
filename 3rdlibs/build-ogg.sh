#!/bin/sh

set -xe

VERSION="1.3.1"
BUILDDIR=`pwd`
DESTDIR="libogg-built"
ARCHS="i386 x86_64 armv7 armv7s arm64"

rm -rf $DESTDIR
mkdir $DESTDIR

if [ ! -e "libogg-$VERSION.zip" ]; then
    curl -LO http://downloads.xiph.org/releases/ogg/libogg-$VERSION.zip
fi

unzip -oq libogg-$VERSION.zip
cd libogg-$VERSION


./configure

for ARCH in $ARCHS;
do
    mkdir -p ../$DESTDIR/$ARCH

    make distclean

    IOSMV="-miphoneos-version-min=4.3"
    case $ARCH in
    arm*)
        if [ $ARCH == "arm64" ]; then
            IOSMV="-miphoneos-version-min=7.0"
        fi
        PATH=`xcodebuild -version -sdk iphoneos PlatformPath`"/Developer/usr/bin:$PATH" \
        SDK=`xcodebuild -version -sdk iphoneos Path` \
        CC="xcrun --sdk iphoneos clang -arch $ARCH $IOSMV --sysroot=$SDK -isystem $SDK/usr/include" \
        CXX="xcrun --sdk iphoneos clang++ -arch $ARCH $IOSMV --sysroot=$SDK -isystem $SDK/usr/include" \
        LDFLAGS="-Wl,-syslibroot,$SDK" \
        ./configure \
            --host=arm-apple-darwin \
        --prefix=$BUILDDIR/$DESTDIR/$ARCH
        ;;
    *)
        PATH=`xcodebuild -version -sdk iphonesimulator PlatformPath`"/Developer/usr/bin:$PATH" \
        CC="xcrun --sdk iphonesimulator clang -arch $ARCH $IOSMV" \
        CXX="xcrun --sdk iphonesimulator clang++ -arch $ARCH $IOSMV" \
        ./configure \
        --prefix=$BUILDDIR/$DESTDIR/$ARCH
        ;;
    esac

    make
    make install
done

make distclean

cd ..
mkdir -p $DESTDIR/universal/lib

INPUT=""
for ARCH in $ARCHS; 
do
    INPUT="$INPUT $DESTDIR/$ARCH/lib/libogg.a"
done
lipo -create $INPUT -output $DESTDIR/universal/lib/libogg.a