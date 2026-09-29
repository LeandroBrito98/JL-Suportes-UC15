@echo off
cd /d "%~dp0"
java -cp "dist\JL_Suportes_UC15_Etapa4.jar;lib\mysql-connector-j-26.7.0.jar" jlsuportes.app.Main
if errorlevel 1 (
  echo.
  echo Nao foi possivel iniciar. Verifique se o JDK 17 ou superior esta instalado e se o MySQL esta configurado conforme README.md.
  pause
)
