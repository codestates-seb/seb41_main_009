import { lazy, Suspense } from 'react';
import { Reset } from 'styled-reset';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios';
import './App.css';

import GlobalStyled from './GlobalStyle';
import PublicRoute from './routes/PublicRoute';
import PrivateRoute from './routes/PrivateRoute';
import ProtectedRoute from './routes/ProtectedRoute';
import SeriesCreatePage from './components/pages/SeriesCreatePage';

axios.defaults.baseURL = 'http://34.64.243.160';
axios.defaults.headers.common.Authorization = 'AUTH_TOKEN';
axios.defaults.withCredentials = true;

const LogIn = lazy(() => import('./components/pages/LogInPage'));
const Header = lazy(() => import('./components/organisms/Header'));
const PostList = lazy(() => import('./components/pages/PostListPage'));
const Showcase = lazy(() => import('./components/pages/ShowcasePage'));
const Sidebar = lazy(() => import('./components/organisms/Sidebar'));
const User = lazy(() => import('./components/pages/UserPage'));
const UserEdit = lazy(() => import('./components/pages/UserEditPage'));
const SeriesListPage = lazy(() => import('./components/pages/SeriesListPage'));
const PostPage = lazy(() => import('./components/pages/PostPage'));
const PostCreatePage = lazy(() => import('./components/pages/PostCreatePage'));
const Search = lazy(() => import('./components/pages/SearchPage'));
const SeriesPage = lazy(() => import('./components/pages/SeriesPage'));
const Signup = lazy(() => import('./components/pages/SignupPage'));
const ErrorPage = lazy(() => import('./components/pages/404ErrorPage'));
const ShowcaseCratePage = lazy(() => import('./components/pages/ShowcaseCreatePage'));

const App = () => {
  return (
    <>
      <Reset />
      <GlobalStyled />
      <BrowserRouter>
        <Header />
        <Main>
          <Sidebar />
          <Suspense>
            <Routes>
              <Route path="/" element={<PublicRoute component={<Showcase />} />} />
              <Route path="/login" element={<PublicRoute component={<LogIn />} />} />
              <Route path="/signup" element={<PublicRoute component={<Signup />} />} />
              <Route path="/showcase/new" element={<PublicRoute component={<ShowcaseCratePage />} />} />
              <Route path="/posts/" element={<PublicRoute component={<PostList />} />} />
              <Route path="/posts/:category" element={<PublicRoute component={<PostList />} />} />
              <Route path="/posts/:category/:id" element={<PublicRoute component={<PostPage />} />} />
              <Route path="/posts/new" element={<ProtectedRoute component={<PostCreatePage />} />} />
              <Route path="/series/:category" element={<PublicRoute component={<SeriesListPage />} />} />
              <Route path="/series/:category/:id" element={<PublicRoute component={<SeriesPage />} />} />
              <Route path="/series/new" element={<PublicRoute component={<SeriesCreatePage />} />} />
              <Route path="/users/:userId" element={<PublicRoute component={<User />} />} />
              <Route path="/users/:userId/edit" element={<PrivateRoute component={<UserEdit />} />} />
              <Route path="/search/:keyword" element={<PublicRoute component={<Search />} />} />
              <Route path="*" element={<PublicRoute component={<ErrorPage />} />} />
            </Routes>
          </Suspense>
        </Main>
      </BrowserRouter>
    </>
  );
};

export default App;

const Main = styled.div`
  display: flex;
  justify-content: center;
  /* margin-top: calc(var(--header-height) + 50px); */
  padding-top: calc(var(--header-height) + 50px);
`;
