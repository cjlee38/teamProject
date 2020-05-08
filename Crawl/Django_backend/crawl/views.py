from django.shortcuts import render, redirect
from .course_table import crawl_Table
from django.contrib import auth
from django.contrib.auth import get_user_model
from .models import *
from .user_table import user_Table
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .serializers import InstSerializer
from django.http import HttpResponse
from rest_framework import status



# @api_view(["GET", "POST"])
# def test(request):
#     if request.method == "GET":
#         queryset = Instruction.objects.all()
#         serializer = InstSerializer(queryset, many =True)
#         return Response(serializer.data)
#     elif request.method == "POST":
#         print(type(request.data))
#         serializer = InstSerializer(data = request.data)
#         if serializer.is_valid():
#             serializer.save()
#             return Response(serializer.data, status = status.HTTP_201_CREATE)
#         return Response(serializer.data, status = status.HTTP_400_BAD_REQUEST)

@api_view(["POST"])
def user_profile(request):
    print(request.data)
    pw = request.data["password"]
    user_Table(str(request.user.student_number), pw, request.user)
    return Response("OK")


    
# class UserAPI(generics.RetrieveAPIView):
#     permission_classes = [permissions.IsAuthenticated]
#     serializer_class = UserSerializer

#     def get_object(self):
#         return self.request.user

def home(request):
    # crawl_Table('14', '1')
    # print(14, 1)
    # crawl_Table('14', '2')
    # print(14, 2)
   
    # crawl_Table('15', '1')
    # print(15, 1)
    # crawl_Table('15', '2')
    # print(15, 2)
    
    # crawl_Table('16', '1')
    # print(16, 1)
    # crawl_Table('16', '2')
    # print(16, 2)

    # crawl_Table('17', '1')
    # print(17, 1)

    # crawl_Table('17', '2')
    # print(17, 2)

    # crawl_Table('18', '1')
    # print(18, 1)

    # crawl_Table('18', '2')
    print(18, 2)


    # crawl_Table('19', '1')
    print(19, 1)
    crawl_Table('19', '2')
    print(19, 2)
    crawl_Table('20', '1')
    print(20, 1)

    User = get_user_model()
    User.objects.all()
    return render(request, 'index.html')

def login(request):
    if request.method=="POST":
        username = request.POST["username"]
        password = request.POST["password"]
        user = auth.authenticate(request, username=username, password=password)
        if user is not None:
            auth.login(request, user)
            return render(request, 'su.html', {'user' : str(request.user)})

def inst(request):
    obj = Instruction.objects.all()
    return render(request, 'list.html', {'obj' : obj})

def signup(request):
    if request.method == "POST":
        user = User.objects.create_user(
                    student_number = request.POST["username"],
                    password = request.POST["password"],
                    name = request.POST["name"],
        )
        auth.login(request, user)
        return render(request, 'su.html')
    else:
        return render(request, 'signup.html')

def user(request):
    # user_Table(id, pw, request.user)
    return render(request, 'su.html')

        
