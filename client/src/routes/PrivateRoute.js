import { Navigate, useParams } from 'react-router-dom';
import styled from 'styled-components';
import useAuthStore from '../store/useAuthStore';

const Container = styled.div`
  width: var(--content-width);
  padding-right: calc(var(--body-width) - var(--content-width) - var(--sidebar-width));
`;

const PrivateRoute = ({ component: Component }) => {
  const { currentUserId } = useAuthStore(state => state);
  const { userId } = useParams();

  if (currentUserId !== Number(userId)) {
    return <Navigate to={`/users/${userId}`} />;
  }

  return <Container>{Component}</Container>;
};

export default PrivateRoute;
