import MainLayout from 'Frontend/views/MainLayout.js';
import { lazy } from 'react';
import { createBrowserRouter, RouteObject } from 'react-router-dom';
import TaskOneView from "Frontend/views/task-one/TaskOneView.js";
import TaskTwoView from "Frontend/views/task-two/TaskTwoView.js";
import TaskThreeView from "Frontend/views/task-three/TaskThreeView.js";

const AboutView = lazy(async () => import('Frontend/views/about/AboutView.js'));

export const routes = [
  {
    element: <MainLayout />,
    handle: { title: 'Main' },
    children: [
      { path: '/', element: <TaskOneView />, handle: { title: 'Task One' } },
      { path: '/task-one', element: <TaskOneView />, handle: { title: 'Task One' } },
      { path: '/task-two', element: <TaskTwoView />, handle: { title: 'Task Two' } },
      { path: '/task-three', element: <TaskThreeView />, handle: { title: 'Task Three' } },
      { path: '/about', element: <AboutView />, handle: { title: 'About' } },
    ],
  },
] as RouteObject[];

export default createBrowserRouter(routes);
