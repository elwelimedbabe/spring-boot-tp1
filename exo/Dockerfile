# =========================================================
# Dockerfile
# يستخدم لنشر مشروع Spring Boot على Render
# =========================================================

# صورة Java 21
FROM eclipse-temurin:21-jdk

# مجلد العمل داخل الحاوية
WORKDIR /app

# نسخ جميع ملفات المشروع
COPY . .

# إعطاء صلاحية تنفيذ mvnw
RUN chmod +x mvnw

# بناء المشروع
RUN ./mvnw clean package -DskipTests

# تشغيل التطبيق باستخدام profile prod
CMD ["java", "-jar", "target/exo-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]