from django.db import models
from django.conf import settings
from django.contrib.auth.models import AbstractBaseUser, BaseUserManager, PermissionsMixin

class UserManager(BaseUserManager):
    def create_user(self, student_number, password=None, **extra_fields):
        user = self.model(student_number=student_number, **extra_fields)
        user.set_password(password)
        user.save(using=self._db)
        return user
    
    def create_superuser(self, student_number, password):
        user = self.create_user(student_number, password)
        user.is_superuser = True
        user.is_staff = True
        user.save(using=self._db)
        return user

class User(AbstractBaseUser, PermissionsMixin):
    user_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100, null=True)
    student_number = models.IntegerField(unique=True)
    major = models.CharField(max_length=100)
    second_major = models.CharField(max_length=100, null=True, blank=True)
    minor = models.CharField(max_length=100, null=True, blank=True)
    year = models.IntegerField(null=True, blank=True)
    foreign = models.BooleanField(default=False)
    teaching = models.BooleanField(default=False)

    is_staff = models.BooleanField(default=False)
    is_admin = models.BooleanField(default=False)

    objects = UserManager()
    USERNAME_FIELD = 'student_number'
    REQUIRED_FIELD = ('student_number', 'name')

    def __str__(self):
        return str(self.student_number)
    
    class Meta:
        db_table="User"


class LiberalArt(models.Model):
    id = models.AutoField(primary_key=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL, models.DO_NOTHING)
    area = models.CharField(max_length=100)
    number_of_subject = models.IntegerField()
    acquisition_credits = models.IntegerField()
    class Meta:
        db_table="Liberal_Art"

class Credit(models.Model):
    credit_id = models.AutoField(primary_key=True)
    user_credit = models.OneToOneField(settings.AUTH_USER_MODEL, models.DO_NOTHING, db_column='user_credit', unique=True)
    first_major = models.IntegerField()
    second_major = models.IntegerField()
    sub_major = models.IntegerField()
    minor = models.IntegerField()
    outdoor = models.IntegerField()
    liberal_arts = models.IntegerField()
    teaching = models.IntegerField()
    total_credit = models.IntegerField()
    optional = models.IntegerField()
    average_score = models.FloatField()
    
    class Meta:
        db_table="Credit"

class Course(models.Model):
    course_id = models.AutoField(primary_key=True)
    user_course = models.ForeignKey(settings.AUTH_USER_MODEL, models.DO_NOTHING, db_column='user_course')
    course_inst_num = models.ForeignKey('Instruction', models.DO_NOTHING, db_column='instruction_number')
    grade = models.CharField(max_length=4)
    
    class Meta:
        db_table="Course"

class Instruction(models.Model):
    Insturction_id = models.AutoField(primary_key=True)
    instruction_number = models.CharField(unique=True, max_length=10)
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
    rq_year = models.CharField(max_length=10)
    rq_semester = models.CharField(max_length=10)
    
    class Meta:
        db_table="Instruction"


class Timetable(models.Model):
    table_id = models.AutoField(primary_key=True)
    user_table = models.ForeignKey(settings.AUTH_USER_MODEL, models.DO_NOTHING, db_column='user_table')
    timetable_inst_num = models.ForeignKey(Instruction, models.DO_NOTHING, db_column='instruction_number')
    
    class Meta:
        db_table="Timetable"


