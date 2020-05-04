from rest_framework import serializers
from .models import *

class UserSerializer(serializers.ModelSerializer) :
    class Meta :
        model = User      
        fields = ('student_number', 'passowrd') 

class InstSerializer(serializers.ModelSerializer):
    class Meta:
        model = Instruction
        fields = ('instruction_number', 'area', 'dept')
        read_only_fields = ('instruction_id',)