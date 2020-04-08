# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Courses(models.Model):
    course_id = models.AutoField(primary_key=True)
    user_course = models.ForeignKey('User', models.DO_NOTHING, db_column='user_course')
    instruction_number = models.ForeignKey('Insturction', models.DO_NOTHING, db_column='instruction_number')

    class Meta:
        managed = False
        db_table = 'Courses'


class Credit(models.Model):
    credit_id = models.AutoField(primary_key=True)
    user_credit = models.ForeignKey('User', models.DO_NOTHING, db_column='user_credit', unique=True)
    first_major = models.IntegerField()
    second_major = models.IntegerField()
    sub_major = models.IntegerField()
    minor = models.IntegerField()
    outdoor = models.IntegerField()
    liberal_arts = models.IntegerField()
    teaching = models.IntegerField()
    total_credit = models.IntegerField()
    optional = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'Credit'


class Insturction(models.Model):
    instruction_number = models.CharField(primary_key=True, max_length=10)
    area = models.CharField(max_length=40)
    dept = models.CharField(max_length=100)
    required = models.IntegerField()
    professor = models.CharField(max_length=100)
    credit = models.IntegerField()
    time = models.IntegerField()
    class_time = models.CharField(max_length=10)
    number_of_people = models.CharField(max_length=25)
    note = models.CharField(max_length=400)
    subject = models.CharField(max_length=200)
    url = models.CharField(max_length=300)
    year = models.IntegerField(blank=True, null=True)
    semester = models.CharField(max_length=10)

    class Meta:
        managed = False
        db_table = 'Insturction'


class Timetable(models.Model):
    table_id = models.AutoField(primary_key=True)
    user_table = models.ForeignKey('User', models.DO_NOTHING, db_column='user_table')
    instruction_number = models.ForeignKey(Insturction, models.DO_NOTHING, db_column='instruction_number')

    class Meta:
        managed = False
        db_table = 'TimeTable'


class User(models.Model):
    user_id = models.AutoField(primary_key=True)
    student_number = models.IntegerField(unique=True)
    password = models.CharField(max_length=45)
    major = models.CharField(max_length=45)

    class Meta:
        managed = False
        db_table = 'User'


class AuthGroup(models.Model):
    name = models.CharField(unique=True, max_length=150)

    class Meta:
        managed = False
        db_table = 'auth_group'


class AuthGroupPermissions(models.Model):
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)
    permission = models.ForeignKey('AuthPermission', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'
        unique_together = (('group', 'permission'),)


class AuthPermission(models.Model):
    name = models.CharField(max_length=255)
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING)
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'
        unique_together = (('content_type', 'codename'),)


class AuthUser(models.Model):
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.IntegerField()
    username = models.CharField(unique=True, max_length=150)
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=150)
    email = models.CharField(max_length=254)
    is_staff = models.IntegerField()
    is_active = models.IntegerField()
    date_joined = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'auth_user'


class AuthUserGroups(models.Model):
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_groups'
        unique_together = (('user', 'group'),)


class AuthUserUserPermissions(models.Model):
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    permission = models.ForeignKey(AuthPermission, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_user_permissions'
        unique_together = (('user', 'permission'),)


class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.PositiveSmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)


class DjangoMigrations(models.Model):
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'
