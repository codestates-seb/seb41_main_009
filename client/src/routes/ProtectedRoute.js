import { Navigate } from 'react-router-dom';
import styled from 'styled-components';
import useAuthStore from '../store/useAuthStore';

const Container = styled.div`
  width: var(--content-width);
  padding-right: calc(var(--body-width) - var(--content-width) - var(--sidebar-width));
`;

const ProtectedRoute = ({ component: Component }) => {
  const { currentUserId } = useAuthStore(state => state);

  if (!currentUserId) {
    return <Navigate to="/login" />;
  }

  return <Container>{Component}</Container>;
};

export default ProtectedRoute;
