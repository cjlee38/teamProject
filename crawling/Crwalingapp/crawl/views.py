from django.shortcuts import render
from .course_table import crawl_Table
# Create your views here.
def home(request):
    # crawl_Table('20', '1')
    return render(request, 'index.html')
