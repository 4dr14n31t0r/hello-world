cd src/
javac main/* -d ../bin
cd ..
mv MANIFEST.MF bin/
cd bin
jar -cvmf MANIFEST.MF Rubik.jar main/*
cd ..
mv bin/MANIFEST.MF ./
mv bin/Rubik.jar ./
chmod 777 Rubik.jar
