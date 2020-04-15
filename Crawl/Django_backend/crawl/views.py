from django.shortcuts import render, redirect
from .course_table import crawl_Table
from django.contrib import auth
from django.contrib.auth import get_user_model
from .models import *
from .user_table import user_Table
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .serializers import UserSerializer



@api_view(["GET"])
def test(request):
    user = get_user_model().objects.get(student_number = request.user.student_number)
    serialize = UserSerializer(user)
    return Response(serialize.data)

    
# class UserAPI(generics.RetrieveAPIView):
#     permission_classes = [permissions.IsAuthenticated]
#     serializer_class = UserSerializer

#     def get_object(self):
#         return self.request.user

def home(request):
    # crawl_Table('16', '1')
    # crawl_Table('16', '2')

    # crawl_Table('17', '1')

    # crawl_Table('17', '2')

    # crawl_Table('18', '1')

    # crawl_Table('18', '2')


    # crawl_Table('19', '1')
    # crawl_Table('19', '2')
    # crawl_Table('20', '1')

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
    obj = Insturction.objects.all()
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
    user_Table('201600786', 'Stork1591!', request.user)
    return render(request, 'su.html')

        
