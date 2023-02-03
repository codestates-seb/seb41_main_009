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
import Footer from './components/organisms/Footer';
import useAuthStore from './store/useAuthStore';

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
const SeriesCreatePage = lazy(() => import('./components/pages/SeriesCreatePage'));
const PostEditPage = lazy(() => import('./components/pages/PostEditPage'));

const App = () => {
  const { authorization } = useAuthStore(state => state);

  axios.defaults.baseURL = 'http://34.64.243.160/';
  axios.defaults.headers.authorization = authorization;

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
              <Route path="/showcase/new" element={<ProtectedRoute component={<ShowcaseCratePage />} />} />
              <Route path="/posts/" element={<PublicRoute component={<PostList />} />} />
              <Route path="/posts/:category" element={<PublicRoute component={<PostList />} />} />
              <Route path="/posts/:category/:id" element={<PublicRoute component={<PostPage />} />} />
              <Route path="/posts/:id/edit" element={<ProtectedRoute component={<PostEditPage />} />} />
              <Route path="/posts/new" element={<ProtectedRoute component={<PostCreatePage />} />} />
              <Route path="/series/" element={<PublicRoute component={<SeriesListPage />} />} />
              <Route path="/series/:category" element={<PublicRoute component={<SeriesListPage />} />} />
              <Route path="/series/:category/:id" element={<PublicRoute component={<SeriesPage />} />} />
              <Route path="/series/new" element={<ProtectedRoute component={<SeriesCreatePage />} />} />
              <Route path="/users/:userId" element={<PublicRoute component={<User />} />} />
              <Route path="/users/:userId/edit" element={<PrivateRoute component={<UserEdit />} />} />
              <Route path="/search/:type" element={<PublicRoute component={<Search />} />} />
              <Route path="*" element={<PublicRoute component={<ErrorPage />} />} />
            </Routes>
          </Suspense>
        </Main>
        <Footer />
      </BrowserRouter>
    </>
  );
};

export default App;

const Main = styled.main`
  display: flex;
  justify-content: center;
  padding: calc(var(--header-height) + 50px) 10px 0 10px;
`;
